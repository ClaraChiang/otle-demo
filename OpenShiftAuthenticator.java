import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.TokenRefreshInterceptor;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;
import io.fabric8.openshift.client.OpenShiftConfig;
import io.fabric8.openshift.client.OpenShiftConfigBuilder;

import java.io.IOException;

public class OpenShiftAuthenticator {

    public static String getAuthToken(String authUrl, String username, String password) {
        try {
            // Build the OpenShift configuration
            OpenShiftConfig config = new OpenShiftConfigBuilder()
                    .withMasterUrl(authUrl)
                    .withUsername(username)
                    .withPassword(password)
                    .build();
            
            // Create the client
            try (OpenShiftClient client = new DefaultOpenShiftClient(config)) {
                // The client will automatically authenticate
                return config.getOauthToken();
            }
        } catch (KubernetesClientException e) {
            System.err.println("Failed to authenticate: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        String authUrl = "https://api.your-openshift-cluster.com:6443";
        String username = "your-username";
        String password = "your-password";
        
        String token = getAuthToken(authUrl, username, password);
        if (token != null) {
            System.out.println("Authentication successful. Token: " + token);
        } else {
            System.out.println("Authentication failed");
        }
    }
}