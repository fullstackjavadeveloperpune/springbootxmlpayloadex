# springbootxmlpayloadex

Spring Boot | XML Request Payload

Steps- 
1. Add Dependency- Jackson XML
2. Use Annotation at POJO Class- @XmlRootElement(name="Customer")
3. In Controller-> Produces, Consumes- MediaType-XML

<customer>
<custId>121</custId>
<custName>SWARA</custName>
</customer>