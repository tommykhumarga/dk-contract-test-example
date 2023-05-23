# Pactflow Contract Test Consumer Example

## How to run

### Pact broker
If you already have pact broker you can skip below step.

`docker-compose -f docker/docker-compose.yml -p="pact-broker" up -d`

Use default pact broker port (9292)

### Create pact file
`npm run test`

or

`npm run test [test-file]`

### Publish consumer to pact broker
`npm run pact:publish -- --broker-base-url=http://localhost:9292 --consumer-app-version=[version]`