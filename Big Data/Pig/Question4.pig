-- Question4

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars);

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = FILTER B1 BY (fullAddress matches '.*Stanford, CA.*');

D = join C by business_id, A1 by business_id;

E = foreach D generate user_id, stars;

F = limit E 10;

dump F;