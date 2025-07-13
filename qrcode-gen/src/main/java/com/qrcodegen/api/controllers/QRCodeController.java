package com.qrcodegen.api.controllers;

import com.qrcodegen.api.dto.QRCodeGenerateRequest;
import com.qrcodegen.api.dto.QRCodeGenerateResponse;
import com.qrcodegen.api.service.QRCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qrcode")
public class QRCodeController {

    QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    public ResponseEntity<QRCodeGenerateResponse> generateQRCode(@RequestBody QRCodeGenerateRequest request) {
        qrCodeService.generateQRCode();
        return null;
    }
}
