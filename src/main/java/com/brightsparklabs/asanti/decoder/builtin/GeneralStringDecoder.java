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
 * Decoder for data of type {@link AsnBuiltinType#GeneralString}
 *
 * @author brightSPARK Labs
 */
public class GeneralStringDecoder extends AbstractBuiltinTypeDecoder<String>
{
    // -------------------------------------------------------------------------
    // INSTANCE VARIABLES
    // -------------------------------------------------------------------------

    /** singleton instance */
    private static GeneralStringDecoder instance;

    // -------------------------------------------------------------------------
    // CONSTRUCTION
    // -------------------------------------------------------------------------

    /**
     * Default constructor.
     *
     * <p>This is private, use {@link #getInstance()} to obtain an instance</p>
     */
    private GeneralStringDecoder() {}

    /**
     * Returns a singleton instance of this class
     *
     * @return a singleton instance of this class
     */
    public static GeneralStringDecoder getInstance()
    {
        if (instance == null)
        {
            instance = new GeneralStringDecoder();
        }
        return instance;
    }

    // -------------------------------------------------------------------------
    // IMPLEMENTATION: AbstractBuiltinTypeDecoder
    // -------------------------------------------------------------------------

    @Override
    public String decode(final byte[] bytes) throws DecodeException
    {
        final ValidationResult validationResult = AsnByteValidator.validateAsGeneralString(bytes);
        DecodeException.throwIfHasFailures(validationResult);
        // TODO: ASN-107 implement
        return null;
    }
}