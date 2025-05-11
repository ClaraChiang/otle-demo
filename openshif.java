import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class OpenShiftVersionChecker {

    public static String getLatestDeployedVersion(String openShiftUrl, String namespace, String deploymentName, String token) throws IOException {
        // Build the URL for the deploymentconfig
        String apiUrl = openShiftUrl + "/apis/apps.openshift.io/v1/namespaces/" + namespace + "/deploymentconfigs/" + deploymentName;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            JSONObject deploymentConfig = new JSONObject(responseBody);

            // Get the latest version from the status
            int latestVersion = deploymentConfig.getJSONObject("status").getInt("latestVersion");

            return String.valueOf(latestVersion);
        }
    }

    public static void main(String[] args) {
        String openShiftUrl = "https://your-openshift-url";
        String namespace = "your-project";
        String deploymentName = "your-deployment";
        String token = "your-token";

        try {
            String latestVersion = getLatestDeployedVersion(openShiftUrl, namespace, deploymentName, token);
            System.out.println("Latest deployed version: " + latestVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}