import { IsEmail, IsNotEmpty, IsOptional } from 'class-validator';

export class CustomerRequest {
  @IsNotEmpty()
  firstName: string;

  @IsOptional()
  lastName?: string;

  @IsNotEmpty()
  @IsEmail()
  email: string;
}

export type CustomerResponse = {
  name: string;
  email: string;
};
