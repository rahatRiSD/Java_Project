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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BkashService {

    @Value("${bkash.api.base-url}")
    private String baseUrl;

    @Value("${bkash.app_key}")
    private String appKey;

    @Value("${bkash.app_secret}")
    private String appSecret;

    @Value("${bkash.username}")
    private String username;

    @Value("${bkash.password}")
    private String password;

    private final RestTemplate restTemplate;

    public BkashService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticate() {
        // Example method to authenticate with Bkash API
        String url = baseUrl + "/tokenized/checkout/token/grant";
        // Add logic to make HTTP POST request with credentials
        return "Authentication token"; // Replace with actual token logic
    }
}