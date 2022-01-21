#!/bin/bash


./consolidate.sh 2>&1 | tee consolidate_output.tmp

if ! grep -q Error consolidate_output.tmp ; then
	#cd ../../.. 	
	#mvn clean package
	
	echo -e "\n\nCleaning unnecessary annotations from generated files..."
	cd ../../../target/generated-sources/spring/src/main/java/com/innofis/omnichannel/ribmob/ac/dto
	for i in $(ls *.java); do 
		sed -i '/@ApiModelProperty\|@Valid\|@javax.annotation.Generated\|@ApiModel\|import io.swagger/d' $i; 
		sed -i '/package io.swagger.model/d' $i; 
		sed -i -e 's/java.time.LocalDate/org.joda.time.LocalDate/g' $i
		sed -i -e 's/AmountReadOnly/Amount/g' $i
		sed -i -e 's/ = null;/;/g' $i

		#if grep -q 'Amount ' $i; then
		#	sed -i '3iimport sa.aljazira.common.rest.model.Amount;' $i
		#fi
        #
		#if grep -q 'BasicField ' $i; then
		#	sed -i '4iimport sa.aljazira.common.rest.model.BasicField;' $i
		#fi

	done
	pcmanfm
	cd -
	#cd info/swagger
	echo -e "\n\nScript ended successfully"
else
	echo -e "\n\nConsolidate do need to be executed WITHOUT any error to continue"
fi


rm consolidate_output.tmp
