import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.openshift.api.model.DeploymentConfig;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;

public class OpenShiftVersionChecker {

    public static String getLatestDeployedVersion(String openShiftUrl, String namespace, String deploymentConfigName, String token) {
        // Configure the client
        Config config = new ConfigBuilder()
                .withMasterUrl(openShiftUrl)
                .withOauthToken(token)
                .build();

        // Create OpenShift client
        try (OpenShiftClient client = new DefaultOpenShiftClient(config)) {
            // Get the DeploymentConfig
            DeploymentConfig dc = client.deploymentConfigs()
                    .inNamespace(namespace)
                    .withName(deploymentConfigName)
                    .get();

            if (dc != null) {
                // Get the latest version
                return String.valueOf(dc.getStatus().getLatestVersion());
            } else {
                return "DeploymentConfig not found";
            }
        }
    }

    public static void main(String[] args) {
        String openShiftUrl = "https://your-openshift-url";
        String namespace = "your-project";
        String deploymentConfigName = "your-deploymentconfig";
        String token = "your-token";

        String latestVersion = getLatestDeployedVersion(openShiftUrl, namespace, deploymentConfigName, token);
        System.out.println("Latest deployed version: " + latestVersion);
    }
}