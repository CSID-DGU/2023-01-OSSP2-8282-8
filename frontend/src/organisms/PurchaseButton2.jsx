import styled from "styled-components";

const Container = styled.TouchableOpacity`
	width: 100%;
	height: 60px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #56AAF6;
	border-radius: 10px;
`;

const ButtonTypo1 = styled.Text`
	font-weight: 600;
	font-size: 16px;
	color: #fff;
`;

const ButtonTypo2 = styled.Text`
	font-weight: 600;
	font-size: 30px;
	color: #fff;
`;

const PurchaseButton2 = ({ typo, onPress }) => {
	return (
		<Container onPress={onPress}>
			<ButtonTypo2>{typo}</ButtonTypo2>
		</Container>
	);
};

export default PurchaseButton2;
