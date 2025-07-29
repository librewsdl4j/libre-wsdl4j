/*
 * (c) Copyright IBM Corp 2004, 2005 
 */

package javax.wsdl.extensions.schema;


import java.io.Serializable;

/**
 * Represents an include or a redefine element within a schema element.
 * 
 * @author Jeremy Hughes &lt;hughesj@uk.ibm.com&gt;
 */
public interface SchemaReference extends Serializable
{
    /**
     * Gets the ID attribute of the referenced schema.
     * 
     * @return the id string 
     */
    String getId();

    /**
     * Sets the ID attribute of the referenced schema.
     * 
     * @param id The id string to set.
     */
    void setId(String id);

    /**
     * Gets the schemaLocation attribute of the referenced schema.
     * 
     * @return the schemaLocation string.
     */
    String getSchemaLocationURI();

    /**
     * Sets the schemaLocation attribute of the referenced schema.
     * 
     * @param schemaLocation The schemaLocation string to set.
     */
    void setSchemaLocationURI(String schemaLocation);

    /**
     * Gets the referenced schema, represented as a LightWeightSchema.
     * 
     * @return the referenced LightWeightSchema.
     */
    Schema getReferencedSchema();

    /**
     * Sets the referenced schema to a LightWeightSchema.
     * 
     * @param referencedSchema The LightWeightSchema to set.
     */
    void setReferencedSchema(Schema referencedSchema);
}