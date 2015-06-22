/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */
package com.brightsparklabs.asanti.reader.parser;

import com.brightsparklabs.asanti.mocks.model.schema.MockAsnSchema;
import com.brightsparklabs.asanti.mocks.model.schema.MockAsnSchemaModule;
import com.brightsparklabs.asanti.model.schema.AsnSchema;
import com.brightsparklabs.asanti.model.schema.AsnSchemaModule;
import com.google.common.base.Splitter;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AsnSchemaParser}
 *
 * @author brightSPARK Labs
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AsnSchemaModuleParser.class)
public class AsnSchemaParserTest
{
    // -------------------------------------------------------------------------
    // FIXTURES
    // -------------------------------------------------------------------------

    /**
     * number of lines expected after pre-parsing the Document-PDU module from the example schema
     */
    private static final int EXPECTED_DOCUMENT_PDU_MODULE_LINE_COUNT = 44;

    /**
     * number of lines expected after pre-parsing the People-Protocol module from the example
     * schema
     */
    private static final int EXPECTED_PEOPLE_PROTOCOL_MODULE_LINE_COUNT = 22;

    /** an invalid schema (missing an END tag) */
    private static final String SCHEMA_NO_END = "People-Protocol\n" +
            "    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) people(2) }\n"
            +
            "\n" +
            "DEFINITIONS\n" +
            "    AUTOMATIC TAGS ::=\n" +
            "\n" +
            "BEGIN\n" +
            "    /*Person ::= SEQUENCE\n" +
            "    {\n" +
            "        firstName [1] OCTET STRING,\n" +
            "        lastName  [2] OCTET STRING,\n" +
            "        title     [3] ENUMERATED\n" +
            "            { mr, mrs, ms, dr, rev } OPTIONAL,\n" +
            "        gender OPTIONAL\n" +
            "    }*/\n" +
            "\n" +
            "    Gender ::= ENUMERATED\n" +
            "    {\n" +
            "        male(0),\n" +
            "        female(1)\n" +
            "    }\n";

    /** a valid schema to check all regex matches */
    private static final String SCHEMA_FOR_REGEX_TEST = "/*Document-PDU\r\n" +
            "    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) document(1) }\n"
            +
            "\n" +
            "DEFINITIONS\n" +
            "    AUTOMATIC TAGS ::=\n" +
            "\n" +
            "IMPORTS\n" +
            "  Gender\n" +
            "    FROM People-Protocol\n" +
            "    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) people(2) }\n"
            +
            "\n*/" +
            "/*********************************\n" +
            "BEGIN\n" +
            "\n" +
            "    Document ::= SEQUENCE\n" +
            "    {\n" +
            "        header [1] Header,\n" +
            "        body   [2] Body,\n" +
            "        footer [3] Footer\n" +
            "    }\n" +
            "END\n" +
            "*********************************/\n" +
            "People-Protocol\n\n" +
            "    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) people(2) }\n"
            +
            "\n" +
            "DEFINITIONS\n" +
            "    AUTOMATIC TAGS ::=\n" +
            "\n" +
            "IMPORTS\n" +
            "  Hair-type\n" +
            "    FROM Hair-Protocol\n" +
            "    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) hair(2) }; BEGIN\n"
            +
            "\n" +
            "    /*Person ::= SEQUENCE\n" +
            "    {\n" +
            "        firstName [1] OCTET STRING,\n" +
            "\t\t\t -- this is a comment --\n" +
            "        lastName  [2] OCTET STRING,\n" +
            "        title     [3] ENUMERATED\n" +
            "            { mr, mrs, ms, dr, rev } OPTIONAL,\n" +
            "        gender OPTIONAL\n" +
            "\t\t\t -- this is a\n" +
            "\t\t\t -- multiline comment --\n" +
            "    }*/\n" +
            "\n" +
            "    Gender ::= ENUMERATED\n" +
            "    {\n" +
            "        male(0),\n" +
            "        female(1)\n" +
            "    }\n" +
            "END\n" +
            "\n" +
            "/*Hair-Protocol\n" +
            "*    { joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) hair(2) }\n"
            +
            "*\n" +
            "*DEFINITIONS\n" +
            "*    AUTOMATIC TAGS ::=\n" +
            "*\n" +
            "*BEGIN\n" +
            "*    Hair ::= SEQUENCE\n" +
            "*    {\n" +
            "*        color [1] OCTET STRING,\n" +
            "*        type  [2] Hair-type\n" +
            "*    }\n" +
            "*\n" +
            "*    Hair-type ::= ENUMERATED\n" +
            "*    {\n" +
            "*        straight(0),\n" +
            "*        curly(1)\n" +
            "*    }\n" +
            "*END*/";

    /**
     * The text expected after applying regex matches to {@link #SCHEMA_FOR_REGEX_TEST}
     */
    private static final String SCHEMA_VALID_PARSE_INPUT = "People-Protocol\n" +
            "{ joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) people(2) }\n"
            +
            "DEFINITIONS\n" +
            "AUTOMATIC TAGS ::=\n" +
            "IMPORTS\n" +
            "Hair-type\n" +
            "FROM Hair-Protocol\n" +
            "{ joint-iso-itu-t internationalRA(23) set(42) set-vendors(9) example(99) modules(2) hair(2) }\n"
            +
            ";\n" +
            "BEGIN\n" +
            "Gender ::= ENUMERATED\n" +
            "{\n" +
            "male(0),\n" +
            "female(1)\n" +
            "}\n" +
            "END";

    // -------------------------------------------------------------------------
    // TESTS
    // -------------------------------------------------------------------------

    @Test
    public void testParse_NullOrEmpty() throws Exception
    {
        // test empty file
        try
        {
            AsnSchemaParser.parse("");
            fail("ParseException not thrown");
        }
        catch (final ParseException ex)
        {
        }

        // test null argument
        try
        {
            AsnSchemaParser.parse(null);
            fail("ParseException not thrown");
        }
        catch (final ParseException ex)
        {
        }
    }

    @Test
    public void testParse_Exceptions() throws Exception
    {
        // test schema with no End keyword
        try
        {
            AsnSchemaParser.parse(SCHEMA_NO_END);
            fail("ParseException not thrown");
        }
        catch (final ParseException ex)
        {
        }
    }

    @Test
    public void testParse() throws Exception
    {/* TODO MJF
        // prepare expected output to AsnSchemaModuleParser.parse()
        final AsnSchemaModule expectedDocumentPduSchemaModule
                = MockAsnSchemaModule.createMockedAsnSchemaModuleForDocumentPdu();
        final AsnSchemaModule expectedPeopleProtocolSchemaModule
                = MockAsnSchemaModule.createMockedAsnSchemaModuleForPeopleProtocol();

        // mock AsnSchemaModuleParser.parse() static method
        PowerMockito.mockStatic(AsnSchemaModuleParser.class);
        final Matcher<Iterable<String>> documentPduMatcher
                = org.hamcrest.Matchers.<String>iterableWithSize(
                EXPECTED_DOCUMENT_PDU_MODULE_LINE_COUNT);
        when(AsnSchemaModuleParser.parse(argThat(documentPduMatcher))).thenReturn(
                expectedDocumentPduSchemaModule);
        final Matcher<Iterable<String>> peopleProtocolMatcher
                = org.hamcrest.Matchers.<String>iterableWithSize(
                EXPECTED_PEOPLE_PROTOCOL_MODULE_LINE_COUNT);
        when(AsnSchemaModuleParser.parse(argThat(peopleProtocolMatcher))).thenReturn(
                expectedPeopleProtocolSchemaModule);

        // parse the example schema
        final AsnSchema actualSchema = AsnSchemaParser.parse(MockAsnSchema.TEST_SCHEMA_TEXT);
        assertNotNull(actualSchema);*/
    }

    @Test
    public void testParse_BlockComments() throws Exception
    {/* TODO MJF
        // prepare objects for mocking of AsnSchemaModuleParser.parse static method
        // set up mock AsnSchemaModule to return
        final AsnSchemaModule mockAsnSchemaModule = AsnSchemaModule.builder()
                .setName("People-Protocol")
                .build();

        // prepare expected input to the AsnSchemaModuleParser.parse static method for
        // valid block comments
        final Splitter newLineSplitter = Splitter.on("\n").omitEmptyStrings();
        final List<String> expectedParseInput
                = newLineSplitter.splitToList(SCHEMA_VALID_PARSE_INPUT);

        // mock AsnSchemaModuleParser.parse static method to only return mock object
        // if argument matches expected input
        PowerMockito.mockStatic(AsnSchemaModuleParser.class);
        when(AsnSchemaModuleParser.parse(expectedParseInput)).thenReturn(mockAsnSchemaModule);

        // test parse with a schema which has all regex combinations
        final AsnSchema actualSchema = AsnSchemaParser.parse(SCHEMA_FOR_REGEX_TEST);
        assertNotNull(actualSchema);*/
    }
}
