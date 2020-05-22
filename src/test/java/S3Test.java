import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.kafka.model.S3;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Builder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.Test;

import java.util.List;

public class S3Test {

    @Test
    public void testS3List(){
        System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true");
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://ec2-13-126-139-80.ap-south-1.compute.amazonaws.com:4566","us-west"))
                .build();
        List<Bucket> buckets = s3Client.listBuckets();
    }
}
