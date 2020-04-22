package org.medibloc.panacea;

import org.medibloc.panacea.domain.*;
import org.medibloc.panacea.domain.aol.AolWriter;
import org.medibloc.panacea.domain.aol.Topic;
import org.medibloc.panacea.domain.bucket.Bucket;
import org.medibloc.panacea.domain.bucket.BucketObject;
import org.medibloc.panacea.domain.bucket.BucketOwner;
import org.medibloc.panacea.domain.bucket.BucketWriter;
import org.medibloc.panacea.domain.did.DIDDocument;
import org.medibloc.panacea.domain.model.response.NodeInfoResponse;
import org.medibloc.panacea.domain.model.response.RecordResponse;
import org.medibloc.panacea.domain.model.response.Res;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PanaceaApi {

    @GET("auth/accounts/{address}")
    Call<Res<Account>> getAccount(@Path("address") String address);

    @GET("node_info")
    Call<NodeInfoResponse> getNodeInfo();

    @POST("txs")
    Call<TxResponse> broadcast(@Body BroadcastReq req);

    @GET("txs/{hash}")
    Call<TxResponse> getTxResponse(@Path("hash") String hash);

    @GET("blocks/{height}")
    Call<BlockInfo> getBlockByHeight(@Path("height") Long height);

    @GET("blocks/latest")
    Call<BlockInfo> getLatestBlock();

    @GET("txs")
    Call<SearchTxsResult> getTxsByHeight(
            @Query("tx.height") Long height,
            @Query("page") int page);


    @GET("txs")
    Call<SearchTxsResult> getTxsByAction(
            @Query("message.action") String action,
            @Query("page") Long page,
            @Query("limit") Long limit);

    /**
     ******************************  DID  ********************************
     */

    @GET("api/v1/did/resolve/{did}")
    Call<Res<DIDDocument>> resolveDID(
        @Path("did") String did
    );

    /**
     ******************************  AOL  *******************************
     */

    @GET("api/v1/aol/{ownerAddr}/topics")
    Call<Res<List<Topic>>> getTopics(@Path("ownerAddr") String ownerAddress);

    @GET("api/v1/aol/{ownerAddr}/topic")
    Call<Res<Topic>> getTopic(@Path("ownerAddr") String ownerAddress);

    @GET("api/v1/aol/{ownerAddr}/listWriter")
    Call<Res<List<String>>> getWriters(
            @Path("ownerAddr") String ownerAddress,
            @Query("topic") String topic);

    @GET("api/v1/aol/{ownerAddr}/writer")
    Call<Res<AolWriter>> getWriter(
            @Path("ownerAddr") String ownerAddress
    );

    @GET("api/v1/aol/{ownerAddr}/topics/{topicName}/records/{offset}")
    Call<Res<RecordResponse>> getRecord(
            @Path("ownerAddr") String ownerAddress,
            @Path("topicName") String topicName,
            @Path("offset") Long offset);

    /**
     ****************************** Bucket *******************************
     */

    @GET("api/v1/bucket/owners")
    Call<Res<List<BucketOwner>>> getOwners();
    @GET("api/v1/bucket/owners/{ownerAddr}")
    Call<Res<BucketOwner>> getOwner(@Path("ownerAddr") String ownerAddr);

    @GET("/api/v1/bucket/owners/{ownerAddr}/buckets")
    Call<Res<List<Bucket>>> getBuckets(@Path("ownerAddr") String ownerAddr);
    @GET("api/v1/bucket/owners/{ownerAddr}/buckets/{bucketName}")
    Call<Res<Bucket>> getBucket(
            @Path("ownerAddr") String ownerAddr,
            @Path("bucketName") String bucketName
    );

    @GET("/api/v1/bucket/owners/{ownerAddr}/buckets/{bucketName}/objects")
    Call<Res<List<BucketObject>>> getBucketObjects(
            @Path("ownerAddr") String ownerAddr,
            @Path("bucketName") String bucketName
    );
    @GET("/api/v1/bucket/owners/{ownerAddr}/buckets/{bucketName}/objects/{objectKey}")
    Call<Res<BucketObject>> getBucketObject(
            @Path("ownerAddr") String ownerAddr,
            @Path("bucketName") String bucketName,
            @Path("objectKey") String objectKey
    );

    @GET("/api/v1/bucket/owners/{ownerAddr}/buckets/{bucketName}/writers")
    Call<Res<List<BucketWriter>>> getBucketWriters(
            @Path("ownerAddr") String ownerAddr,
            @Path("bucketName") String bucketName
    );
    @GET("/api/v1/bucket/owners/{ownerAddr}/buckets/{bucketName}/writers/{writerAddr}")
    Call<Res<BucketWriter>> getBucketWriter(
            @Path("ownerAddr") String ownerAddr,
            @Path("bucketName") String bucketName,
            @Path("writerAddr") String writerAddr
    );

}
