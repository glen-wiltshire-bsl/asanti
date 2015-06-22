package com.brightsparklabs.asanti.model.schema.type;

import com.brightsparklabs.asanti.model.schema.AsnBuiltinType;
import com.brightsparklabs.asanti.model.schema.constraint.AsnSchemaConstraint;
import com.brightsparklabs.asanti.model.schema.primitive.AsnPrimitiveType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import static com.google.common.base.Preconditions.*;

/**
 * A concrete implementation of class that can model the types for objects within ASN.1 schema.
 * These objects can be either Type Definitions, eg Type ::= SomeType,
 * or components within a constructed type (SEQUENCE etc), eg component SomeType
 *
 *
 * @author brightSPARK Labs
 */
public class AbstractAsnSchemaType implements AsnSchemaType
{
    // -------------------------------------------------------------------------
    // INSTANCE VARIABLES
    // -------------------------------------------------------------------------

    /** the primitiveType of this definition */
    private final AsnPrimitiveType primitiveType;

    /** the constraint on the primitiveType */
    protected final AsnSchemaConstraint constraint;

    /** the parent type if this type is defined in terms on another */
    //private final AsnSchemaType indirectType;

    // -------------------------------------------------------------------------
    // CONSTRUCTION
    // -------------------------------------------------------------------------

    /**
     * Default constructor.
     *
     * @param primitiveType
     *            the underlying primitiveType of the defined primitiveType
     *
     * @param constraint
     *            The constraint on the type. Use
     *            {@link AsnSchemaConstraint#NULL} if no constraint.
     *            <p>
     *            Example 1<br>
     *            For {@code SET (SIZE (1..100) OF OCTET STRING (SIZE (10))}
     *            this would be {@code (SIZE (10)}.
     *            <p>
     *            Example 2<br>
     *            For {@code INTEGER (1..256)} this would be {@code (1..256)}.
     *
     * @throws NullPointerException
     *             if {@code name} or {@code builtinType} are {@code null}
     *
     * @throws IllegalArgumentException
     *             if {@code name} is blank
     */
    public AbstractAsnSchemaType(AsnPrimitiveType primitiveType, AsnSchemaConstraint constraint)
    {
        checkNotNull(primitiveType);

        this.primitiveType = primitiveType;
        this.constraint = (constraint == null) ? AsnSchemaConstraint.NULL : constraint;

    }


    // -------------------------------------------------------------------------
    // IMPLEMENTATION
    // -------------------------------------------------------------------------


    @Override
    public AsnPrimitiveType getPrimitiveType()
    {
        return primitiveType;
    }

    @Override
    public AsnBuiltinType getBuiltinType()
    {
        // getPrimitiveType may be overridden in some derived classes.
        // by calling it here it means we are less likely to need to override this function too.
        return getPrimitiveType().getBuiltinType();
    }

    @Override
    public ImmutableSet<AsnSchemaConstraint> getConstraints()
    {
        int breakpoint = 0; // TODO MJF
        return ImmutableSet.<AsnSchemaConstraint>of(constraint);
    }

    @Override
    public AsnSchemaType getChildType(String tag)
    {
        return AsnSchemaType.NULL;
    }

    @Override
    public String getChildName(String tag)
    {
        return "";
    }
/*
    @Override
    public AsnSchemaType getType()
    {
        return this;
    }
*/

}
