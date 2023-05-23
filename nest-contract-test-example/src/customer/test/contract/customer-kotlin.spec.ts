import * as request from 'supertest';
import { pactWith } from 'jest-pact';
import { CustomerRequest } from '../../customer.dto';
import { Matchers } from '@pact-foundation/pact';
import { somethingLike } from '@pact-foundation/pact/src/dsl/matchers';

pactWith(
  {
    consumer: 'nest-contract-test-example',
    provider: 'kotlin-contract-test-example',
    logLevel: 'debug',
    timeout: 5000,
    pactfileWriteMode: 'update',
  },
  (provider) => {
    it('should respond with bad request', async () => {
      const mockRequest: CustomerRequest = {
        firstName: 'John',
        email: 'johndoe@example.com',
      };

      await provider.addInteraction({
        state: 'bad request',
        uponReceiving: 'Create customer',
        withRequest: {
          method: 'POST',
          path: '/customer',
          body: {
            firstName: Matchers.string('John'),
            email: Matchers.string('johndoe@example.com'),
          },
        },
        willRespondWith: {
          status: 400,
          headers: {
            'Content-Type': 'application/json',
          },
          body: {
            message: somethingLike('Bad request'),
          },
        },
      });

      await request(provider.mockService.baseUrl)
        .post('/customer')
        .send(mockRequest)
        .expect(400)
        .expect({
          message: 'Bad request',
        });
    });

    it('should respond with newly created customer', async () => {
      const mockRequest: CustomerRequest = {
        firstName: 'John',
        lastName: 'Doe',
        email: 'johndoe@example.com',
      };

      await provider.addInteraction({
        state: 'normal',
        uponReceiving: 'Create customer',
        withRequest: {
          method: 'POST',
          path: '/customer',
          body: {
            firstName: Matchers.string('John'),
            lastName: Matchers.string('Doe'),
            email: Matchers.string('johndoe@example.com'),
          },
        },
        willRespondWith: {
          status: 200,
          headers: {
            'Content-Type': 'application/json',
          },
          body: {
            name: somethingLike('John Doe'),
            email: somethingLike('johndoe@example.com'),
          },
        },
      });

      await request(provider.mockService.baseUrl)
        .post('/customer')
        .send(mockRequest)
        .expect(200)
        .expect({
          name: 'John Doe',
          email: 'johndoe@example.com',
        });
    });
  },
);
