import styled from "styled-components";

const Container = styled.TouchableOpacity`
	width: 100%;
	height: 100px;
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
	font-size: 35px;
	color: #fff;
`;

const PurchaseButton = ({ typo, onPress }) => {
	return (
		<Container onPress={onPress}>
			<ButtonTypo1>{typo}</ButtonTypo1>
			<ButtonTypo2>결제하기</ButtonTypo2>
		</Container>
	);
};

export default PurchaseButton;
