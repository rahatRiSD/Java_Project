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
package org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.controller;

import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentRequestDto;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.dto.PaymentCategoryDto;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.service.PaymentService;
import org.thingsboard.server.leave_module.src.main.java.com.employee.leaveRequest.paymentMethod.service.impl.BkashService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thingsboard.server.controller.BaseController;
import org.thingsboard.server.queue.util.TbCoreComponent;

import java.util.List;
@TbCoreComponent
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController extends BaseController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BkashService bkashService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        String response = paymentService.processPayment(paymentRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/make-payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequestDto paymentRequest) {
        // Convert amount to String before passing to the service
        String amountAsString = String.valueOf(paymentRequest.getAmount());
//        return bkashService.initiatePayment(amountAsString, paymentRequest.getMerchantInvoiceNumber());
        return  ResponseEntity.ok(amountAsString);
    }

    // Create a new Bkash payment
    @PostMapping("/bkash")
    public ResponseEntity<PaymentCategoryDto> createBkashPayment(@RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto createdBkash = paymentService.createBkashPayment(paymentCategoryDto);
        return ResponseEntity.ok(createdBkash);
    }


    @GetMapping("/bkash")
    public ResponseEntity<List<PaymentCategoryDto>> getAllBkashPayments() {
        List<PaymentCategoryDto> bkashPayments = paymentService.getAllBkashPayments();
        return ResponseEntity.ok(bkashPayments);
    }

    // Get a specific Bkash payment by ID
    @GetMapping("/bkash/{id}")
    public ResponseEntity<PaymentCategoryDto> getBkashPaymentById(@PathVariable Long id) {
        PaymentCategoryDto bkashPayment = paymentService.getBkashPaymentById(id);
        return ResponseEntity.ok(bkashPayment);
    }

    
    @PutMapping("/bkash/{id}")
    public ResponseEntity<PaymentCategoryDto> updateBkashPayment(@PathVariable Long id, @RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto updatedBkash = paymentService.updateBkashPayment(id, paymentCategoryDto);
        return ResponseEntity.ok(updatedBkash);
    }

   
    @DeleteMapping("/bkash/{id}")
    public ResponseEntity<Void> deleteBkashPayment(@PathVariable Long id) {
        paymentService.deleteBkashPayment(id);
        return ResponseEntity.noContent().build();
    }

    
    @PostMapping("/nagad")
    public ResponseEntity<PaymentCategoryDto> createNagadPayment(@RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto createdNagad = paymentService.createNagadPayment(paymentCategoryDto);
        return ResponseEntity.ok(createdNagad);
    }

    // Get all Nagad payments
    @GetMapping("/nagad")
    public ResponseEntity<List<PaymentCategoryDto>> getAllNagadPayments() {
        List<PaymentCategoryDto> nagadPayments = paymentService.getAllNagadPayments();
        return ResponseEntity.ok(nagadPayments);
    }

    // Get a specific Nagad payment by ID
    @GetMapping("/nagad/{id}")
    public ResponseEntity<PaymentCategoryDto> getNagadPaymentById(@PathVariable Long id) {
        PaymentCategoryDto nagadPayment = paymentService.getNagadPaymentById(id);
        return ResponseEntity.ok(nagadPayment);
    }

    // Update a Nagad payment
    @PutMapping("/nagad/{id}")
    public ResponseEntity<PaymentCategoryDto> updateNagadPayment(@PathVariable Long id, @RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto updatedNagad = paymentService.updateNagadPayment(id, paymentCategoryDto);
        return ResponseEntity.ok(updatedNagad);
    }

    // Delete a Nagad payment
    @DeleteMapping("/nagad/{id}")
    public ResponseEntity<Void> deleteNagadPayment(@PathVariable Long id) {
        paymentService.deleteNagadPayment(id);
        return ResponseEntity.noContent().build();
    }

    // Create a new Visa payment
    @PostMapping("/visa")
    public ResponseEntity<PaymentCategoryDto> createVisaPayment(@RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto createdVisa = paymentService.createVisaPayment(paymentCategoryDto);
        return ResponseEntity.ok(createdVisa);
    }

    // Get all Visa payments
    @GetMapping("/visa")
    public ResponseEntity<List<PaymentCategoryDto>> getAllVisaPayments() {
        List<PaymentCategoryDto> visaPayments = paymentService.getAllVisaPayments();
        return ResponseEntity.ok(visaPayments);
    }

    // Get a specific Visa payment by ID
    @GetMapping("/visa/{id}")
    public ResponseEntity<PaymentCategoryDto> getVisaPaymentById(@PathVariable Long id) {
        PaymentCategoryDto visaPayment = paymentService.getVisaPaymentById(id);
        return ResponseEntity.ok(visaPayment);
    }

    // Update a Visa payment
    @PutMapping("/visa/{id}")
    public ResponseEntity<PaymentCategoryDto> updateVisaPayment(@PathVariable Long id, @RequestBody PaymentCategoryDto paymentCategoryDto) {
        PaymentCategoryDto updatedVisa = paymentService.updateVisaPayment(id, paymentCategoryDto);
        return ResponseEntity.ok(updatedVisa);
    }

    // Delete a Visa payment
    @DeleteMapping("/visa/{id}")
    public ResponseEntity<Void> deleteVisaPayment(@PathVariable Long id) {
        paymentService.deleteVisaPayment(id);
        return ResponseEntity.noContent().build();
    }
}