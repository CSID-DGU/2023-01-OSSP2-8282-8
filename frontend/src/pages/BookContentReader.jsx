import * as React from "react";
import { View } from "react-native";
import PDFReader from "rn-pdf-reader-js";
import Header from "../organisms/Header";

const BookContentReader = ({ navigation }) => {
	return (
		<>
			<Header navigation={navigation} />
			<PDFReader
				source={{
					uri: "http://samples.leanpub.com/thereactnativebook-sample.pdf",
				}}
			/>
		</>
	);
};

export default BookContentReader;
