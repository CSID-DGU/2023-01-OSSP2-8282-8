import styled from "styled-components";
import React from "react";
import { Image, View } from "react-native";

const Container = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	box-sizing: border-box;
	margin: 0 60px;
	padding: 0 15px;
`;
const Container2 = styled.TouchableOpacity`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const Nametypo = styled.Text`
	font-size: 16px;
`;
const ListContainer = ({ navigation, products, type, isMyLib = false }) => {
	return (
		<Container>
			{products.map((product) => {
				return (
					<Container2
						key={type == "book" ? product.bookId : product.noteId}
						onPress={() => {
							isMyLib
								? navigation.navigate("BookContentReader")
								: navigation.navigate(
										type.charAt(0).toUpperCase() + type.slice(1) + "Detail",
										{
											id: type == "book" ? product.bookId : product.noteId,
										}
								  );
						}}
					>
						<Image
							source={{ uri: product.bookCover }}
							style={{ width: 130, height: 190 }}
						/>
						<Container2>
							<View
								style={{ width: 140, display: "flex", alignItems: "center" }}
							>
								<Nametypo>
									{product[type + "Title"].length > 16
										? product[type + "Title"].slice(0, 14) + "..."
										: product[type + "Title"]}
								</Nametypo>
							</View>
						</Container2>
					</Container2>
				);
			})}
		</Container>
	);
};

export default ListContainer;
