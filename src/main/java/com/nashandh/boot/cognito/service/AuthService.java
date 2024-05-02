package com.nashandh.boot.cognito.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUserPoolsResponse;

@Service
public class AuthService {

    // TODO as part of security config - move cognito client to a config class and make it an bean
    //  then automagic it
    private CognitoIdentityProviderClient cognitoIdentityProviderClient;
    public AuthService() {
        cognitoIdentityProviderClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }

    // TODO check clean arch guidelines - this should probably accept specific params
    //  instead of Controller model since it is a service - other code outside of AuthController
    //  wouldn't be able to use this
    //
    //  re-enable this and adjust it to use real/new things as part of enabling security so we can log in as a cognito user
//    public boolean loginUser(AuthLoginRequest userRequest) {
//        try {
//            AdminInitiateAuthRequest request = AdminInitiateAuthRequest.builder()
//                .userPoolId("cognito-userpool-id") // use environment variable
//                .clientId("cognito-client-id") // use environment variable
//                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
//                .authParameters(Collections.singletonMap("USERNAME", userRequest.getUsername()));
//
//            AdminInitiateAuthResponse authResponse = cognitoIdentityProvider.adminInitiateAuth(authRequest);
//            if (ChallengeNameType.PASSWORD_VERIFIER.toString().equals(authResponse.getChallengeName())) {
//                return true;
//            }
//
//        } catch (Exception e) {
//            // Handle login errors
//            return false;
//        }
//        return false;
//    }

    // TODO remove me - only used for initial testing of AWS SDK cognito stuff
    public void listUserPools() {
        try {
            ListUserPoolsRequest request = ListUserPoolsRequest.builder()
                    .maxResults(10)
                    .build();

            ListUserPoolsResponse response = cognitoIdentityProviderClient.listUserPools(request);
            response.userPools().forEach(userpool -> {
                System.out.println("User pool " + userpool.name() + ", User ID " + userpool.id());
            });

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}