import styled from "styled-components";
import React from "react";
import { Image } from "react-native";

const Container = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	box-sizing: border-box;
	margin: 0 60px;
	padding: 0 15px;
`;
const Container2 = styled.TouchableOpacity`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin: 0 25px;
`;

const Nametypo = styled.Text`
	font-size: 20px;
`;
const ListContainer = ({ navigation, products, type }) => {
	return (
		<Container>
			{products.map((product) => {
				return (
					<Container2
						key={type == "book" ? product.bookId : product.noteId}
						onPress={() => {
							navigation.navigate(
								type.charAt(0).toUpperCase() + type.slice(1) + "Detail",
								{
									id: type == "book" ? product.bookId : product.noteId,
								}
							);
						}}
					>
						<Image
							source={{
								uri: "https://pdfampus.s3.ap-northeast-2.amazonaws.com/1.jpg",
								//uri: {product.bookCover}
								// product.bookCover
							}}
							style={{ width: 130, height: 190 }}
						/>
						<Container2>
							<Nametypo>{product[type + "Title"]}</Nametypo>
						</Container2>
					</Container2>
				);
			})}
		</Container>
	);
};

export default ListContainer;
//{product[type + "Title"]/*{product.title} */}
//[type + "Id"]
