import { HttpException, Injectable } from '@nestjs/common';
import { CustomerRequest, CustomerResponse } from './customer.dto';
import { HttpService } from '@nestjs/axios';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class CustomerRepository {
  private baseUrl: string;
  constructor(
    private readonly configService: ConfigService,
    private readonly httpService: HttpService,
  ) {
    this.baseUrl = this.configService.get('KOTLIN_PACT_BASE_URL');
  }

  async findAll(): Promise<CustomerResponse[]> {
    try {
      const { data } = await this.httpService.axiosRef.get(
        `${this.baseUrl}/customer`,
      );
      return data;
    } catch (e) {
      throw new HttpException(e.response.data.message, e.response.status);
    }
  }

  async create(request: CustomerRequest): Promise<CustomerResponse> {
    try {
      const { data } = await this.httpService.axiosRef.post(
        `${this.baseUrl}/customer`,
        request,
      );
      return data;
    } catch (e) {
      throw new HttpException(e.response.data.message, e.response.status);
    }
  }
}
