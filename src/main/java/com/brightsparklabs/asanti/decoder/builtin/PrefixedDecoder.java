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
 * Decoder for data of type {@link AsnBuiltinType#Prefixed}
 *
 * @author brightSPARK Labs
 */
public class PrefixedDecoder extends AbstractBuiltinTypeDecoder<String>
{
    // -------------------------------------------------------------------------
    // INSTANCE VARIABLES
    // -------------------------------------------------------------------------

    /** singleton instance */
    private static PrefixedDecoder instance;

    // -------------------------------------------------------------------------
    // CONSTRUCTION
    // -------------------------------------------------------------------------

    /**
     * Default constructor.
     *
     * <p>This is private, use {@link #getInstance()} to obtain an instance</p>
     */
    private PrefixedDecoder() {}

    /**
     * Returns a singleton instance of this class
     *
     * @return a singleton instance of this class
     */
    public static PrefixedDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new PrefixedDecoder();
        }
        return instance;
    }

    // -------------------------------------------------------------------------
    // IMPLEMENTATION: AbstractBuiltinTypeDecoder
    // -------------------------------------------------------------------------

    @Override
    public String decode(final byte[] bytes) throws DecodeException
    {
        final ValidationResult validationResult = AsnByteValidator.validateAsPrefixed(bytes);
        DecodeException.throwIfHasFailures(validationResult);
        // TODO: ASN-107 implement
        return null;
    }
}