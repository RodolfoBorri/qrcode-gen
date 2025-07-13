package com.qrcodegen.api.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.qrcodegen.api.dto.QRCodeGenerateResponse;
import com.qrcodegen.api.infrastructure.S3StorageAdapter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@Service
public class QRCodeService {

    private final S3StorageAdapter s3StorageAdapter;

    public QRCodeService(S3StorageAdapter s3StorageAdapter) {
        this.s3StorageAdapter = s3StorageAdapter;
    }

    public QRCodeGenerateResponse generateAndUploadQRCode(String text) throws Exception {
        byte[] qrCodeData = generateQRCode(text);

        String generatedUrl = s3StorageAdapter.uploadFile(qrCodeData, UUID.randomUUID().toString(), "image/png");

        return new QRCodeGenerateResponse(generatedUrl);
    }

    private byte[] generateQRCode(String text) throws Exception{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        return outputStream.toByteArray();
    }
}
