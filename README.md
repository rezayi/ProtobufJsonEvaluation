A test code has been developed to evaluate the impact of serialization and deserialization using JSON and Protobuf. 
Several considerations were made for this test:
* A small count of records can be unreliable. Results are based on the average of one million records to minimize the impact of outliers.
* System processing conditions can influence results. Tests were repeated 10 times to validate consistency.
* Record size impacts performance; hence, it was selected as a key parameter.
* Certain cases, like transferring HTML content, significantly increase text size in JSON. This scenario was tested separately.
