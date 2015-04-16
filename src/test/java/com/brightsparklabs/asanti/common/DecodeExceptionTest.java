/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */

package com.brightsparklabs.asanti.common;

import com.brightsparklabs.asanti.validator.FailureType;
import com.brightsparklabs.asanti.validator.result.DecodedDataValidationResult;
import com.brightsparklabs.asanti.validator.result.DecodedAsnDataValidationResultImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link DecodeException}
 *
 * @author brightSPARK Labs
 */
public class DecodeExceptionTest
{
    // -------------------------------------------------------------------------
    // TESTS
    // -------------------------------------------------------------------------

    @Test
    public void testDecodeException() throws Exception
    {
        DecodeException instance = new DecodeException("");
        assertEquals("", instance.getMessage());
        instance = new DecodeException(null);
        assertEquals(null, instance.getMessage());
        instance = new DecodeException("TEST");
        assertEquals("TEST", instance.getMessage());
    }

    @Test
    public void testThrowIfHasFailures() throws Exception
    {
        // test with no validation failures
        DecodedDataValidationResult validationResult = DecodedAsnDataValidationResultImpl.builder().build();
        DecodeException.throwIfHasFailures(validationResult);

        // test with validation failures
        validationResult =
                DecodedAsnDataValidationResultImpl.builder()
                        .add("TEST_LOCATION", FailureType.DataIncorrectlyFormatted, "TEST_REASON")
                        .build();
        try
        {
            DecodeException.throwIfHasFailures(validationResult);
            fail("DecodeException not thrown");
        }
        catch (DecodeException ex)
        {
        }
    }
}
