/*
 * Created by brightSPARK Labs
 * www.brightsparklabs.com
 */
package com.brightsparklabs.asanti.mocks.model.schema;

import com.brightsparklabs.asanti.model.schema.AsnBuiltinType;
import com.brightsparklabs.asanti.model.schema.constraint.AsnSchemaConstraint;
import com.brightsparklabs.asanti.model.schema.typedefinition.AbstractAsnSchemaTypeDefinition;
import com.google.common.collect.ImmutableList;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.*;

/**
 * Utility class for obtaining mocked instances of {@link AbstractAsnSchemaTypeDefinition} which
 * conform to the test ASN.1 schema defined in the {@code README.md} file
 *
 * @author brightSPARK Labs
 */
public class MockAsnSchemaTypeDefinition
{
    // -------------------------------------------------------------------------
    // PUBLIC METHODS
    // -------------------------------------------------------------------------

    /**
     * Default constructor. This is hidden, use {@link #builder(String, AsnBuiltinType)} instead.
     */
    private MockAsnSchemaTypeDefinition()
    {
        // private constructor
    }

    /**
     * Return a builder for creating instances of {@link MockAsnSchemaTypeDefinition}
     *
     * @param name
     *         name of the defined type
     * @param builtinType
     *         the underlying ASN.1 type of the defined type
     */
    public static MockAsnSchemaTypeDefinitionBuilder builder(String name,
            AsnBuiltinType builtinType)
    {
        return new MockAsnSchemaTypeDefinitionBuilder(name, builtinType);
    }

    /**
     * Creates mock {@link AbstractAsnSchemaTypeDefinition} instances conforming to the
     * 'Document-PDU' module in the test ASN.1 schema defined in the {@code README.md} file
     *
     * @return mock instances for use within the 'Document-PDU' module
     */
    public static ImmutableList<AbstractAsnSchemaTypeDefinition> createMockedAsnSchemaTypeDefinitionsForDocumentPdu()
    {
        final ImmutableList.Builder<AbstractAsnSchemaTypeDefinition> listBuilder
                = ImmutableList.builder();

        // build Document
        AbstractAsnSchemaTypeDefinition mocktypeDefinition = MockAsnSchemaTypeDefinition.builder(
                "Document",
                AsnBuiltinType.Sequence)
                .addComponentType("1", "header", "Header")
                .addComponentType("2", "body", "Body")
                .addComponentType("3", "footer", "Footer")
                .addComponentType("4", "dueDate", "Date-Due")
                .addComponentType("5", "version", "generated.Document.version")
                .addComponentType("6", "description", "generated.Document.description")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Header
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Header", AsnBuiltinType.Sequence)
                .addComponentType("0", "published", "PublishedMetadata")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Body
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Body", AsnBuiltinType.Sequence)
                .addComponentType("0", "lastModified", "ModificationMetadata")
                .addComponentType("1", "prefix", "Section-Note")
                .addComponentType("2", "content", "Section-Main")
                .addComponentType("3", "suffix", "Section-Note")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Footer
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Footer", AsnBuiltinType.Set)
                .addComponentType("0", "authors", "People")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build PublishedMetadata
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("PublishedMetadata",
                AsnBuiltinType.Sequence)
                .addComponentType("1", "date", "GeneralizedTime")
                .addComponentType("2", "country", "OCTET STRING")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build ModificationMetadata
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("ModificationMetadata",
                AsnBuiltinType.Sequence)
                .addComponentType("0", "date", "DATE")
                .addComponentType("1", "modifiedBy", "Person")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Section-Note
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Section-Note",
                AsnBuiltinType.Sequence).addComponentType("1", "text", "OCTET STRING").build();
        listBuilder.add(mocktypeDefinition);

        // build Section-Main
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Section-Main",
                AsnBuiltinType.Sequence)
                .addComponentType("1", "text", "OCTET STRING")
                .addSeqeunceOfComponentType("2", "paragraphs", "SEQUENCE OF Paragraph")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Paragraph
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Paragraph",
                AsnBuiltinType.Sequence)
                .addComponentType("1", "title", "OCTET STRING")
                .addComponentType("2", "contributor", "Person")
                .addSeqeunceOfComponentType("3", "points", "SEQUENCE OF OCTET STRING")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build References
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("References",
                AsnBuiltinType.SequenceOf).build();
        listBuilder.add(mocktypeDefinition);

        // build Date-Due
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Date-Due", AsnBuiltinType.Integer)
                .build();
        listBuilder.add(mocktypeDefinition);

        return listBuilder.build();
    }

    /**
     * Creates mock {@link AbstractAsnSchemaTypeDefinition} instances conforming to the
     * 'People-Protocol' module in the test ASN.1 schema defined in the {@code README.md} file
     *
     * @return mock instances for use within the 'PeopleProtocol' module
     */
    public static ImmutableList<AbstractAsnSchemaTypeDefinition> createMockedAsnSchemaTypeDefinitionsForPeopleProtocol()
    {
        final ImmutableList.Builder<AbstractAsnSchemaTypeDefinition> listBuilder
                = ImmutableList.builder();

        // build People
        AbstractAsnSchemaTypeDefinition mocktypeDefinition = MockAsnSchemaTypeDefinition.builder(
                "People",
                AsnBuiltinType.SetOf).build();
        listBuilder.add(mocktypeDefinition);

        // build Person
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Person", AsnBuiltinType.Sequence)
                .addComponentType("1", "firstName", "OCTET STRING")
                .addComponentType("2", "lastName", "OCTET STRING")
                .addComponentType("3", "title", "generated.Person.title")
                .addComponentType("4", "gender", "Gender")
                .addComponentType("5", "maritalStatus", "generated.Person.maritalStatus")
                .build();
        listBuilder.add(mocktypeDefinition);

        // build Gender
        mocktypeDefinition = MockAsnSchemaTypeDefinition.builder("Gender",
                AsnBuiltinType.Enumerated).build();
        listBuilder.add(mocktypeDefinition);

        return listBuilder.build();
    }

    // -------------------------------------------------------------------------
    // INTERNAL CLASS: MockedInstanceBuilder
    // -------------------------------------------------------------------------

    /**
     * Builder for creating mocked instances of {@link AbstractAsnSchemaTypeDefinition}
     *
     * @author brightSPARK Labs
     */
    public static class MockAsnSchemaTypeDefinitionBuilder
    {
        // ---------------------------------------------------------------------
        // INSTANCE VARIABLES
        // ---------------------------------------------------------------------

        final AbstractAsnSchemaTypeDefinition mockedInstance
                = mock(AbstractAsnSchemaTypeDefinition.class);

        // ---------------------------------------------------------------------
        // CONSTRUCTION
        // ---------------------------------------------------------------------

        /**
         * Default constructor
         *
         * @param name
         *         name of the defined type
         * @param builtinType
         *         the underlying ASN.1 type of the defined type
         */
        private MockAsnSchemaTypeDefinitionBuilder(String name, AsnBuiltinType builtinType)
        {
            when(mockedInstance.getBuiltinType()).thenReturn(builtinType);
            when(mockedInstance.getName()).thenReturn(name);
            when(mockedInstance.getConstraint()).thenReturn(AsnSchemaConstraint.NULL);
        }

        // ---------------------------------------------------------------------
        // PUBLIC METHODS
        // ---------------------------------------------------------------------

        /**
         * Sets the constraint on this definition
         *
         * @param constraint
         *         constraint to use
         *
         * @return this builder
         */
        public MockAsnSchemaTypeDefinitionBuilder setConstraint(AsnSchemaConstraint constraint)
        {
            when(mockedInstance.getConstraint()).thenReturn(constraint);
            return this;
        }

        /**
         * Add a component type to this definition
         *
         * @param tag
         *         tag of the component type
         * @param tagName
         *         tag name of the component type
         * @param typeName
         *         type name of the component type
         *
         * @return this builder
         */
        public MockAsnSchemaTypeDefinitionBuilder addComponentType(String tag, String tagName,
                String typeName)
        {
            when(mockedInstance.getTagName(tag)).thenReturn(tagName);
            when(mockedInstance.getTypeName(tag)).thenReturn(typeName);
            return this;
        }

        /**
         * Add a SEQUENCE OF component type to this definition
         *
         * @param tag
         *         tag of the component type
         * @param tagName
         *         tag name of the component type
         * @param typeName
         *         type name of the SEQUENCE OF component type E.g. {@code "SEQUENCE OF Paragraph"}
         *
         * @return this builder
         */
        public MockAsnSchemaTypeDefinitionBuilder addSeqeunceOfComponentType(String tag,
                String tagName, String typeName)
        {
            final String elementTypeName = typeName.replace("SEQUENCE OF ", "");
            return addCollectionOfComponentType(tag, tagName, elementTypeName);
        }

        /**
         * Add a SET OF component type to this definition
         *
         * @param tag
         *         tag of the component type
         * @param tagName
         *         tag name of the component type
         * @param typeName
         *         type name of the SET OF component type E.g. {@code "SET OF Person"}
         *
         * @return this builder
         */
        public MockAsnSchemaTypeDefinitionBuilder addSetOfComponentType(String tag, String tagName,
                String typeName)
        {
            final String elementTypeName = typeName.replace("SET OF ", "");
            return addCollectionOfComponentType(tag, tagName, elementTypeName);
        }

        /**
         * Creates a mocked instance from the data in this builder
         *
         * @return a mocked instance of {@link AbstractAsnSchemaTypeDefinition}
         */
        public AbstractAsnSchemaTypeDefinition build()
        {
            return mockedInstance;
        }

        // ---------------------------------------------------------------------
        // PRIVATE METHODS
        // ---------------------------------------------------------------------

        /**
         * Add a collection of (SEQUENCE OF/SET OF) component type to this definition
         *
         * @param tag
         *         tag of the component type
         * @param tagName
         *         tag name of the component type
         * @param elementTypeName
         *         type name of the SEQUENCE OF or SET OF element type E.g. for {@code "SET OF
         *         Person"} this would be {@code "Person"}
         *
         * @return this builder
         */
        private MockAsnSchemaTypeDefinitionBuilder addCollectionOfComponentType(String tag,
                final String tagName, String elementTypeName)
        {
            // handle without index supplied
            when(mockedInstance.getTagName(tag)).thenReturn(tagName);
            when(mockedInstance.getTypeName(tag)).thenReturn(elementTypeName);

            // handle with index supplied
            final String regex = tag + "\\[\\d+\\]";
            when(mockedInstance.getTagName(matches(regex))).thenAnswer(new Answer<String>()
            {
                @Override
                public String answer(InvocationOnMock invocation) throws Throwable
                {
                    final String rawTag = (String) invocation.getArguments()[0];
                    final String tagIndex = rawTag.replaceFirst(".+(\\[.+\\])", "$1");
                    return tagName + tagIndex;
                }
            });
            when(mockedInstance.getTypeName(matches(regex))).thenReturn(elementTypeName);
            return this;
        }
    }
}
