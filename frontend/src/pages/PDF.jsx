import React from "react";
import { StyleSheet, Dimensions, View, Text } from "react-native";
import Pdf from "react-native-pdf";

import Header from "../organisms/Header";

const PDF = ({ navigation }) => {
	const source = {
		uri: "http://samples.leanpub.com/thereactnativebook-sample.pdf",
		cache: true,
	};
	console.log("src:", source);

	return (
		<View>
			<Header navigation={navigation} />
			<Text>this is pdf</Text>
			{/* <Pdf
				source={source}
				onLoadComplete={(numberOfPages, filePath) => {
					console.log(`Number of pages: ${numberOfPages}`);
				}}
				onPageChanged={(page, numberOfPages) => {
					console.log(`Current page: ${page}`);
				}}
				onError={(error) => {
					console.log(error);
				}}
				onPressLink={(uri) => {
					console.log(`Link pressed: ${uri}`);
				}}
			/> */}
		</View>
	);
};

export default PDF;
