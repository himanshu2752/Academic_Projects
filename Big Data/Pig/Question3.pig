-- Question3

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars);

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = COGROUP A1 by business_id, B1 by business_id;

D = limit C 5 ;

Dump D;