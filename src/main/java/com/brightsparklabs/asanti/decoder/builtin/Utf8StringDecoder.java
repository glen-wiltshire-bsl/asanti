/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */

package com.brightsparklabs.asanti.decoder.builtin;

import com.brightsparklabs.asanti.common.DecodeException;
import com.brightsparklabs.asanti.model.schema.AsnBuiltinType;
import com.brightsparklabs.asanti.validator.ValidationResult;
import com.brightsparklabs.asanti.validator.bytes.AsnByteValidator;

/**
 * Decoder for data of type {@link AsnBuiltinType#Utf8String}
 *
 * @author brightSPARK Labs
 */
public class Utf8StringDecoder extends AbstractBuiltinTypeDecoder<String>
{
    // -------------------------------------------------------------------------
    // INSTANCE VARIABLES
    // -------------------------------------------------------------------------

    /** singleton instance */
    private static Utf8StringDecoder instance;

    // -------------------------------------------------------------------------
    // CONSTRUCTION
    // -------------------------------------------------------------------------

    /**
     * Default constructor.
     *
     * <p>This is private, use {@link #getInstance()} to obtain an instance</p>
     */
    private Utf8StringDecoder() {}

    /**
     * Returns a singleton instance of this class
     *
     * @return a singleton instance of this class
     */
    public static Utf8StringDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new Utf8StringDecoder();
        }
        return instance;
    }

    // -------------------------------------------------------------------------
    // IMPLEMENTATION: AbstractBuiltinTypeDecoder
    // -------------------------------------------------------------------------

    @Override
    public String decode(final byte[] bytes) throws DecodeException
    {
        final ValidationResult validationResult = AsnByteValidator.validateAsUtf8String(bytes);
        DecodeException.throwIfHasFailures(validationResult);
        // TODO: ASN-107 implement
        return null;
    }
}