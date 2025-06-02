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
package org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.service.impl;

import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentRequestDto;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentCategoryDto;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.entity.Bkash;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.entity.Nagad;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.entity.Visa;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.repository.BkashRepository;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.repository.NagadRepository;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.repository.VisaRepository;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final BkashRepository bkashRepository;

    @Autowired
    private final NagadRepository nagadRepository;

    @Autowired
    private final VisaRepository visaRepository;

    @Override
    public String processPayment(PaymentRequestDto paymentRequestDto) {
        if ("bkash".equalsIgnoreCase(paymentRequestDto.getMethod())) {
            Bkash bkash = Bkash.builder()
                    .accountNumber(paymentRequestDto.getAccountNumber())
                    .balance(paymentRequestDto.getAmount())
                    .transactionId("TXN" + System.currentTimeMillis())
                    .build();
            bkashRepository.save(bkash);
            return "Bkash payment processed successfully with Transaction ID: " + bkash.getTransactionId();
        } else if ("nagad".equalsIgnoreCase(paymentRequestDto.getMethod())) {
            Nagad nagad = Nagad.builder()
                    .accountNumber(paymentRequestDto.getAccountNumber())
                    .balance(paymentRequestDto.getAmount())
                    .transactionId("TXN" + System.currentTimeMillis())
                    .build();
            nagadRepository.save(nagad);
            return "Nagad payment processed successfully with Transaction ID: " + nagad.getTransactionId();
        } else if ("visa".equalsIgnoreCase(paymentRequestDto.getMethod())) {
            Visa visa = Visa.builder()
                    .cardNumber(paymentRequestDto.getCardNumber())
                    .balance(paymentRequestDto.getAmount())
                    .transactionId(paymentRequestDto.getTransactionId())
                    .build();
            visaRepository.save(visa);
            return "Visa payment processed successfully with Transaction ID: " + visa.getTransactionId();
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentRequestDto.getMethod());
        }
    }

    // CRUD operations for Bkash
    @Override
    public PaymentCategoryDto createBkashPayment(PaymentCategoryDto paymentCategoryDto) {
        Bkash bkash = Bkash.builder()
                .accountNumber(paymentCategoryDto.getAccountNumber())
                .balance(paymentCategoryDto.getBalance())
                .transactionId(paymentCategoryDto.getTransactionId())
                .build();
        bkashRepository.save(bkash);
        return paymentCategoryDto;
    }

    @Override
    public List<PaymentCategoryDto> getAllBkashPayments() {
        return bkashRepository.findAll().stream().map(bkash -> new PaymentCategoryDto(
                bkash.getAccountNumber(),
                bkash.getBalance(),
                bkash.getTransactionId()
        )).collect(Collectors.toList());
    }

    @Override
    public PaymentCategoryDto getBkashPaymentById(Long id) {
        Bkash bkash = bkashRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bkash payment not found"));
        return new PaymentCategoryDto(bkash.getAccountNumber(), bkash.getBalance(), bkash.getTransactionId());
    }

    @Override
    public PaymentCategoryDto updateBkashPayment(Long id, PaymentCategoryDto paymentCategoryDto) {
        Bkash bkash = bkashRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bkash payment not found"));
        bkash.setAccountNumber(paymentCategoryDto.getAccountNumber());
        bkash.setBalance(paymentCategoryDto.getBalance());
        bkash.setTransactionId(paymentCategoryDto.getTransactionId());
        bkashRepository.save(bkash);
        return paymentCategoryDto;
    }

    @Override
    public void deleteBkashPayment(Long id) {
        bkashRepository.deleteById(id);
    }

    // CRUD operations for Nagad
    @Override
    public PaymentCategoryDto createNagadPayment(PaymentCategoryDto paymentCategoryDto) {
        Nagad nagad = Nagad.builder()
                .accountNumber(paymentCategoryDto.getAccountNumber())
                .balance(paymentCategoryDto.getBalance())
                .transactionId(paymentCategoryDto.getTransactionId())
                .amount(paymentCategoryDto.getBalance()) // Set amount field
                .status("PENDING") // Set default status
                .build();
        nagadRepository.save(nagad);
        return paymentCategoryDto;
    }

    @Override
    public List<PaymentCategoryDto> getAllNagadPayments() {
        return nagadRepository.findAll().stream().map(nagad -> new PaymentCategoryDto(
                nagad.getAccountNumber(),
                nagad.getBalance(),
                nagad.getTransactionId()
        )).collect(Collectors.toList());
    }

    @Override
    public PaymentCategoryDto getNagadPaymentById(Long id) {
        Nagad nagad = nagadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nagad payment not found"));
        return new PaymentCategoryDto(nagad.getAccountNumber(), nagad.getBalance(), nagad.getTransactionId());
    }

    @Override
    public PaymentCategoryDto updateNagadPayment(Long id, PaymentCategoryDto paymentCategoryDto) {
        Nagad nagad = nagadRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nagad payment not found"));
        nagad.setAccountNumber(paymentCategoryDto.getAccountNumber());
        nagad.setBalance(paymentCategoryDto.getBalance());
        nagad.setTransactionId(paymentCategoryDto.getTransactionId());
        nagadRepository.save(nagad);
        return paymentCategoryDto;
    }

    @Override
    public void deleteNagadPayment(Long id) {
        nagadRepository.deleteById(id);
    }

    // CRUD operations for Visa
    @Override
    public PaymentCategoryDto createVisaPayment(PaymentCategoryDto paymentCategoryDto) {
        Visa visa = Visa.builder()
                .cardNumber(paymentCategoryDto.getCardNumber())
                .balance(paymentCategoryDto.getBalance())
                .transactionId(paymentCategoryDto.getTransactionId())
                .build();
        visaRepository.save(visa);
        return paymentCategoryDto;
    }

    @Override
    public List<PaymentCategoryDto> getAllVisaPayments() {
        return visaRepository.findAll().stream().map(visa -> new PaymentCategoryDto(
                visa.getCardNumber(),
                visa.getBalance(),
                visa.getTransactionId()
        )).collect(Collectors.toList());
    }

    @Override
    public PaymentCategoryDto getVisaPaymentById(Long id) {
        Visa visa = visaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Visa payment not found"));
        return new PaymentCategoryDto(visa.getCardNumber(), visa.getBalance(), visa.getTransactionId());
    }

    @Override
    public PaymentCategoryDto updateVisaPayment(Long id, PaymentCategoryDto paymentCategoryDto) {
        Visa visa = visaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Visa payment not found"));
        visa.setCardNumber(paymentCategoryDto.getCardNumber());
        visa.setBalance(paymentCategoryDto.getBalance());
        visa.setTransactionId(paymentCategoryDto.getTransactionId());
        visaRepository.save(visa);
        return paymentCategoryDto;
    }

    @Override
    public void deleteVisaPayment(Long id) {
        visaRepository.deleteById(id);
    }
}