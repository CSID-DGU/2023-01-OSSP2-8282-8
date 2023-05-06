import styled from "styled-components";
import React from "react";
import { Image } from "react-native";

const Container = styled.View`
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
	margin-left: 60px;
	margin-right: 60px;
`;
const Container2 = styled.View`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const Nametypo = styled.Text`
	font-size: 20;
`;
const ListContainer = ({ products }) => {
	return (
		<Container>
			{products.map((product) => (
				<Container2 key={product.id}>
					<Image
						source={{ uri: product.image }}
						style={{ width: 130, height: 190 }}
					/>
					<Container2>
						<Nametypo>{product.name}</Nametypo>
					</Container2>
				</Container2>
			))}
		</Container>
	);
};

export default ListContainer;
