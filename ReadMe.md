Localstack Java Integration Working Sample Demonstration
=========================================================

It has three test suite:

1. Locally hosted localstack docker in test/java/dockerLocalstackTest.java. It is using localstack-test-utils and aws sdk.
2. Locatstack which is running localstack start --host connected via aws sdk in DynamoDBTest.java.
3. S3Test.java is S3 similarly to DynamoDBTest.java

How to Run
--------------
$> mvn install

$> mvn compile

$> mvn test

How to start localstack
------------------------
Please refer : https://github.com/localstack/localstack/tree/v0.11.1 