/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */

/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */

package com.brightsparklabs.asanti.validator.bytes;

import com.brightsparklabs.asanti.decoder.AsnByteDecoder;
import com.brightsparklabs.asanti.model.schema.AsnBuiltinType;
import com.brightsparklabs.asanti.validator.FailureType;
import com.brightsparklabs.asanti.validator.ValidationResult;
import com.brightsparklabs.asanti.validator.ValidationResultImpl;

/**
 * Utility class for decoding bytes from ASN.1 String Types. <p/> This class was created to prevent
 * {@link AsnByteDecoder} from containing too much logic. The
 * methods in here are designed to be called by {@link AsnByteDecoder}
 * (hence why they use package visibility). Do not call the methods in this class directly. Use
 * {@link AsnByteDecoder} instead.
 *
 * @author brightSPARK Labs
 */
class AsnStringByteValidator
{
    // -------------------------------------------------------------------------
    // CONSTRUCTION
    // -------------------------------------------------------------------------

    /**
     * Default constructor
     */
    private AsnStringByteValidator()
    {
        // constructor should never be called for static class
        assert false;
    }

    // -------------------------------------------------------------------------
    //  PACKAGE METHODS
    // -------------------------------------------------------------------------

    /*
     * NOTE: Please keep these methods ordered alphabetically for simplicity
     */

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#BitString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsBitString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#BmpString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsBmpString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#CharacterString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsCharacterString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#GeneralString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsGeneralString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#GraphicString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsGraphicString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#Ia5String}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsIa5String(byte[] bytes)
    {
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        if (builder.containsResults())
        {
            // bytes were null, do not continue validating
            return builder.build();
        }

        for (byte b : bytes)
        {
            if (b < 0 || b > 127)
            {
                final String error =
                        "Supplied bytes do not conform to the IA5String format. All bytes must be within the range 0 - 127. Supplied bytes contain a byte with value: "
                                + b;
                builder.add("Byte with value: " + b, FailureType.DataIncorrectlyFormatted, error);
            }
        }
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#Iso646String}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsIso646String(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#NumericString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsNumericString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#OctetString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result. No transformation is done for bytes in an OCTET STRING. I.e.
     * the bytes are returned as is.
     */
    static ValidationResult validateAsOctetString(byte[] bytes)
    {
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#PrintableString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */

    static ValidationResult validateAsPrintableString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#TeletexString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsTeletexString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#UniversalString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsUniversalString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#Utf8String}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsUtf8String(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#VideotexString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsVideotexString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }

    /**
     * Validates the supplied bytes as an {@link AsnBuiltinType#VisibleString}
     *
     * @param bytes
     *         bytes to validate
     *
     * @return the validation result
     */
    static ValidationResult validateAsVisibleString(byte[] bytes)
    {
        // TODO: ASN-105
        final ValidationResultImpl.Builder builder
                = AsnByteValidator.validationResultBuilderFor(bytes);
        return builder.build();
    }
}
