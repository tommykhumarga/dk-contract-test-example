# Pactflow Contract Test Provider Example

## How to run

### Pact broker

If you already have pact broker you can skip below step.

`docker-compose -f docker/docker-compose.yml -p="pact-broker" up -d`

Use default pact broker port (9292)

### Verify locally

Run test in the test file, don't forget to set `host` and `port` in @PactBroker annotation

### Verify in pact broker

`mvn pact:verify`

### Publish

`PACT_VERIFIER_PUBLISH=true mvn -Dtest='com/example/kotlincontracttestexample/contract/provider/*' test --define pactbroker.scheme=http --define pactbroker.host=localhost:9292`