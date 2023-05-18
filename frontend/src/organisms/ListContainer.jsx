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
const Container2 = styled.View`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	box-sizing: border-box;
	margin: 0 10px;
`;

const Nametypo = styled.Text`
	font-size: 20;
`;
const ListContainer = ({ products, type }) => {
	return (
		<Container>
			{products.map((product) => {
				return (
					<Container2 key={product.bookId}>
						<Image
							source={{
								uri: product.bookCover,
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
