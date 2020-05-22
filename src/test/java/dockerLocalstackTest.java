import cloud.localstack.LocalstackTestRunner;
import cloud.localstack.TestUtils;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(LocalstackTestRunner.class)
//@LocalstackDockerProperties(services = { "s3", "sqs", "kinesis:77077" })
@LocalstackDockerProperties(randomizePorts = true, services = { "sqs", "kinesis", "s3", "sns", "dynamodb", "cloudwatch" })
public class dockerLocalstackTest {

    @Test
    public void testLocalS3API() {
        AmazonS3 s3 = TestUtils.getClientS3();
        List<Bucket> buckets = s3.listBuckets();

    }

    @Test
    public void testDynamoDBAPI() throws InterruptedException {
        DynamoDB db = new DynamoDB(TestUtils.getClientDynamoDB());
        //List<KeySchemaElement> schemaElements =new ArrayList<KeySchemaElement>(){};
        //ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput();
        //List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>() {};
        Table table = db.createTable("Music",
                Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
                        // key
                        new KeySchemaElement("title", KeyType.RANGE)), // Sort key
                Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
                        new AttributeDefinition("title", ScalarAttributeType.S)),
                new ProvisionedThroughput(10L, 10L));
        table.waitForActive();
        TableCollection<ListTablesResult> tables = db.listTables();
        boolean status = false;
        for (Table ltable : tables) {
            if(ltable.getTableName().equals("Music")){
                status = true;
                ltable.delete();
            }
        }
        assert status;
    }

}
