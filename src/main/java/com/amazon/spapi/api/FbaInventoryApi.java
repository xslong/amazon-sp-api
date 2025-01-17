/*
 * Selling Partner API for FBA Inventory
 * The Selling Partner API for FBA Inventory lets you programmatically retrieve information about inventory in Amazon's fulfillment network.
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.amazon.spapi.api;

import com.amazon.spapi.client.*;
import com.amazon.spapi.SellingPartnerAPIAA.*;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import com.amazon.spapi.model.fbainventory.GetInventorySummariesResponse;
import org.threeten.bp.OffsetDateTime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FbaInventoryApi {
    private ApiClient apiClient;

    FbaInventoryApi() {
        this(Configuration.getDefaultApiClient());
    }

    public FbaInventoryApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getInventorySummaries
     * @param granularityType The granularity type for the inventory aggregation level. (required)
     * @param granularityId The granularity ID for the inventory aggregation level. (required)
     * @param marketplaceIds The marketplace ID for the marketplace for which to return inventory summaries. (required)
     * @param details true to return inventory summaries with additional summarized inventory details and quantities. Otherwise, returns inventory summaries only (default value). (optional, default to false)
     * @param startDateTime A start date and time in ISO8601 format. If specified, all inventory summaries that have changed since then are returned. You must specify a date and time that is no earlier than 18 months prior to the date and time when you call the API. Note: Changes in inboundWorkingQuantity, inboundShippedQuantity and inboundReceivingQuantity are not detected. (optional)
     * @param sellerSkus A list of seller SKUs for which to return inventory summaries. You may specify up to 50 SKUs. (optional)
     * @param nextToken String token returned in the response of your previous request. (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public okhttp3.Call getInventorySummariesCall(String granularityType, String granularityId, List<String> marketplaceIds, Boolean details, OffsetDateTime startDateTime, List<String> sellerSkus, String nextToken, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/fba/inventory/v1/summaries";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (details != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("details", details));
        if (granularityType != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("granularityType", granularityType));
        if (granularityId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("granularityId", granularityId));
        if (startDateTime != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("startDateTime", startDateTime));
        if (sellerSkus != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("csv", "sellerSkus", sellerSkus));
        if (nextToken != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("nextToken", nextToken));
        if (marketplaceIds != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("csv", "marketplaceIds", marketplaceIds));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new okhttp3.Interceptor() {
                @Override
                public okhttp3.Response intercept(okhttp3.Interceptor.Chain chain) throws IOException {
                    okhttp3.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getInventorySummariesValidateBeforeCall(String granularityType, String granularityId, List<String> marketplaceIds, Boolean details, OffsetDateTime startDateTime, List<String> sellerSkus, String nextToken, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'granularityType' is set
        if (granularityType == null) {
            throw new ApiException("Missing the required parameter 'granularityType' when calling getInventorySummaries(Async)");
        }
        
        // verify the required parameter 'granularityId' is set
        if (granularityId == null) {
            throw new ApiException("Missing the required parameter 'granularityId' when calling getInventorySummaries(Async)");
        }
        
        // verify the required parameter 'marketplaceIds' is set
        if (marketplaceIds == null) {
            throw new ApiException("Missing the required parameter 'marketplaceIds' when calling getInventorySummaries(Async)");
        }
        

        okhttp3.Call call = getInventorySummariesCall(granularityType, granularityId, marketplaceIds, details, startDateTime, sellerSkus, nextToken, progressListener, progressRequestListener);
        return call;

    }

    /**
     * 
     * Returns a list of inventory summaries. The summaries returned depend on the presence or absence of the startDateTime and sellerSkus parameters:  - All inventory summaries with available details are returned when the startDateTime and sellerSkus parameters are omitted. - When startDateTime is provided, the operation returns inventory summaries that have had changes after the date and time specified. The sellerSkus parameter is ignored. - When the sellerSkus parameter is provided, the operation returns inventory summaries for only the specified sellerSkus.  **Usage Plan:**  | Rate (requests per second) | Burst | | ---- | ---- | | 90 | 150 |  For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     * @param granularityType The granularity type for the inventory aggregation level. (required)
     * @param granularityId The granularity ID for the inventory aggregation level. (required)
     * @param marketplaceIds The marketplace ID for the marketplace for which to return inventory summaries. (required)
     * @param details true to return inventory summaries with additional summarized inventory details and quantities. Otherwise, returns inventory summaries only (default value). (optional, default to false)
     * @param startDateTime A start date and time in ISO8601 format. If specified, all inventory summaries that have changed since then are returned. You must specify a date and time that is no earlier than 18 months prior to the date and time when you call the API. Note: Changes in inboundWorkingQuantity, inboundShippedQuantity and inboundReceivingQuantity are not detected. (optional)
     * @param sellerSkus A list of seller SKUs for which to return inventory summaries. You may specify up to 50 SKUs. (optional)
     * @param nextToken String token returned in the response of your previous request. (optional)
     * @return GetInventorySummariesResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public GetInventorySummariesResponse getInventorySummaries(String granularityType, String granularityId, List<String> marketplaceIds, Boolean details, OffsetDateTime startDateTime, List<String> sellerSkus, String nextToken) throws ApiException {
        ApiResponse<GetInventorySummariesResponse> resp = getInventorySummariesWithHttpInfo(granularityType, granularityId, marketplaceIds, details, startDateTime, sellerSkus, nextToken);
        return resp.getData();
    }

    /**
     * 
     * Returns a list of inventory summaries. The summaries returned depend on the presence or absence of the startDateTime and sellerSkus parameters:  - All inventory summaries with available details are returned when the startDateTime and sellerSkus parameters are omitted. - When startDateTime is provided, the operation returns inventory summaries that have had changes after the date and time specified. The sellerSkus parameter is ignored. - When the sellerSkus parameter is provided, the operation returns inventory summaries for only the specified sellerSkus.  **Usage Plan:**  | Rate (requests per second) | Burst | | ---- | ---- | | 90 | 150 |  For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     * @param granularityType The granularity type for the inventory aggregation level. (required)
     * @param granularityId The granularity ID for the inventory aggregation level. (required)
     * @param marketplaceIds The marketplace ID for the marketplace for which to return inventory summaries. (required)
     * @param details true to return inventory summaries with additional summarized inventory details and quantities. Otherwise, returns inventory summaries only (default value). (optional, default to false)
     * @param startDateTime A start date and time in ISO8601 format. If specified, all inventory summaries that have changed since then are returned. You must specify a date and time that is no earlier than 18 months prior to the date and time when you call the API. Note: Changes in inboundWorkingQuantity, inboundShippedQuantity and inboundReceivingQuantity are not detected. (optional)
     * @param sellerSkus A list of seller SKUs for which to return inventory summaries. You may specify up to 50 SKUs. (optional)
     * @param nextToken String token returned in the response of your previous request. (optional)
     * @return ApiResponse&lt;GetInventorySummariesResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<GetInventorySummariesResponse> getInventorySummariesWithHttpInfo(String granularityType, String granularityId, List<String> marketplaceIds, Boolean details, OffsetDateTime startDateTime, List<String> sellerSkus, String nextToken) throws ApiException {
        okhttp3.Call call = getInventorySummariesValidateBeforeCall(granularityType, granularityId, marketplaceIds, details, startDateTime, sellerSkus, nextToken, null, null);
        Type localVarReturnType = new TypeToken<GetInventorySummariesResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     *  (asynchronously)
     * Returns a list of inventory summaries. The summaries returned depend on the presence or absence of the startDateTime and sellerSkus parameters:  - All inventory summaries with available details are returned when the startDateTime and sellerSkus parameters are omitted. - When startDateTime is provided, the operation returns inventory summaries that have had changes after the date and time specified. The sellerSkus parameter is ignored. - When the sellerSkus parameter is provided, the operation returns inventory summaries for only the specified sellerSkus.  **Usage Plan:**  | Rate (requests per second) | Burst | | ---- | ---- | | 90 | 150 |  For more information, see \&quot;Usage Plans and Rate Limits\&quot; in the Selling Partner API documentation.
     * @param granularityType The granularity type for the inventory aggregation level. (required)
     * @param granularityId The granularity ID for the inventory aggregation level. (required)
     * @param marketplaceIds The marketplace ID for the marketplace for which to return inventory summaries. (required)
     * @param details true to return inventory summaries with additional summarized inventory details and quantities. Otherwise, returns inventory summaries only (default value). (optional, default to false)
     * @param startDateTime A start date and time in ISO8601 format. If specified, all inventory summaries that have changed since then are returned. You must specify a date and time that is no earlier than 18 months prior to the date and time when you call the API. Note: Changes in inboundWorkingQuantity, inboundShippedQuantity and inboundReceivingQuantity are not detected. (optional)
     * @param sellerSkus A list of seller SKUs for which to return inventory summaries. You may specify up to 50 SKUs. (optional)
     * @param nextToken String token returned in the response of your previous request. (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public okhttp3.Call getInventorySummariesAsync(String granularityType, String granularityId, List<String> marketplaceIds, Boolean details, OffsetDateTime startDateTime, List<String> sellerSkus, String nextToken, final ApiCallback<GetInventorySummariesResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        okhttp3.Call call = getInventorySummariesValidateBeforeCall(granularityType, granularityId, marketplaceIds, details, startDateTime, sellerSkus, nextToken, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<GetInventorySummariesResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }

    public static class Builder {
        private AWSAuthenticationCredentials awsAuthenticationCredentials;
        private LWAAuthorizationCredentials lwaAuthorizationCredentials;
        private String endpoint;
        private LWAAccessTokenCache lwaAccessTokenCache;
        private Boolean disableAccessTokenCache = false;
        private AWSAuthenticationCredentialsProvider awsAuthenticationCredentialsProvider;

        public Builder awsAuthenticationCredentials(AWSAuthenticationCredentials awsAuthenticationCredentials) {
            this.awsAuthenticationCredentials = awsAuthenticationCredentials;
            return this;
        }

        public Builder lwaAuthorizationCredentials(LWAAuthorizationCredentials lwaAuthorizationCredentials) {
            this.lwaAuthorizationCredentials = lwaAuthorizationCredentials;
            return this;
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }
        
        public Builder lwaAccessTokenCache(LWAAccessTokenCache lwaAccessTokenCache) {
            this.lwaAccessTokenCache = lwaAccessTokenCache;
            return this;
        }
		
	   public Builder disableAccessTokenCache() {
            this.disableAccessTokenCache = true;
            return this;
        }
        
        public Builder awsAuthenticationCredentialsProvider(AWSAuthenticationCredentialsProvider awsAuthenticationCredentialsProvider) {
            this.awsAuthenticationCredentialsProvider = awsAuthenticationCredentialsProvider;
            return this;
        }
        

        public FbaInventoryApi build() {
            if (awsAuthenticationCredentials == null) {
                throw new RuntimeException("AWSAuthenticationCredentials not set");
            }

            if (lwaAuthorizationCredentials == null) {
                throw new RuntimeException("LWAAuthorizationCredentials not set");
            }

            if (StringUtil.isEmpty(endpoint)) {
                throw new RuntimeException("Endpoint not set");
            }

            AWSSigV4Signer awsSigV4Signer;
            if ( awsAuthenticationCredentialsProvider == null) {
                awsSigV4Signer = new AWSSigV4Signer(awsAuthenticationCredentials);
            }
            else {
                awsSigV4Signer = new AWSSigV4Signer(awsAuthenticationCredentials,awsAuthenticationCredentialsProvider);
            }
            
            LWAAuthorizationSigner lwaAuthorizationSigner = null;            
            if (disableAccessTokenCache) {
                lwaAuthorizationSigner = new LWAAuthorizationSigner(lwaAuthorizationCredentials);
            }
            else {
                if (lwaAccessTokenCache == null) {
                    lwaAccessTokenCache = new LWAAccessTokenCacheImpl();                  
                 }
                 lwaAuthorizationSigner = new LWAAuthorizationSigner(lwaAuthorizationCredentials,lwaAccessTokenCache);
            }

            return new FbaInventoryApi(new ApiClient()
                .setAWSSigV4Signer(awsSigV4Signer)
                .setLWAAuthorizationSigner(lwaAuthorizationSigner)
                .setBasePath(endpoint));
        }
    }
}
