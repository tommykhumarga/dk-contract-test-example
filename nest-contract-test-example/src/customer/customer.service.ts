import { Injectable } from '@nestjs/common';
import { CustomerRepository } from './customer.repository';
import { CustomerRequest, CustomerResponse } from './customer.dto';

@Injectable()
export class CustomerService {
  constructor(private readonly customerRepository: CustomerRepository) {}

  async findAll() {
    return this.customerRepository.findAll();
  }

  create(request: CustomerRequest): Promise<CustomerResponse> {
    return this.customerRepository.create(request);
  }
}
