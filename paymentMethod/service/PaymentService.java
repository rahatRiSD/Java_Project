/**
 * Copyright © 2016-2024 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.service;

import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentRequestDto;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentCategoryDto;
import java.util.List;

public interface PaymentService {
    String processPayment(PaymentRequestDto paymentRequestDto);

    // CRUD operations for Bkash
    PaymentCategoryDto createBkashPayment(PaymentCategoryDto paymentCategoryDto);
    List<PaymentCategoryDto> getAllBkashPayments();
    PaymentCategoryDto getBkashPaymentById(Long id);
    PaymentCategoryDto updateBkashPayment(Long id, PaymentCategoryDto paymentCategoryDto);
    void deleteBkashPayment(Long id);

    // CRUD operations for Nagad
    PaymentCategoryDto createNagadPayment(PaymentCategoryDto paymentCategoryDto);
    List<PaymentCategoryDto> getAllNagadPayments();
    PaymentCategoryDto getNagadPaymentById(Long id);
    PaymentCategoryDto updateNagadPayment(Long id, PaymentCategoryDto paymentCategoryDto);
    void deleteNagadPayment(Long id);

    // CRUD operations for Visa
    PaymentCategoryDto createVisaPayment(PaymentCategoryDto paymentCategoryDto);
    List<PaymentCategoryDto> getAllVisaPayments();
    PaymentCategoryDto getVisaPaymentById(Long id);
    PaymentCategoryDto updateVisaPayment(Long id, PaymentCategoryDto paymentCategoryDto);
    void deleteVisaPayment(Long id);
}