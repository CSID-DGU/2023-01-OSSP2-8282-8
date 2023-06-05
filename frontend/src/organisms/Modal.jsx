import styled from "styled-components";
import { Modal } from "react-native";

const ModalContiner = styled.View`
	height: 100%;
	justify-content: center;
	align-items: center;
`;

const BlockTypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
`;

const Block = styled.View`
	width: 400px;
	height: 300px;
	align-items: center;
	justify-content: space-around;
	border-radius: 20px;
	border-width: 2px;
	border-color: #ccc;
	background: white;
`;

const ButtonsContainer = styled.View`
	width: 80%;
	display: flex;
	flex-direction: row;
	justify-content: space-around;
`;
const ButtonWrapper = styled.TouchableOpacity`
	width: 140px;
	height: 66px;
	align-items: center;
	justify-content: center;
	border-radius: 11px;
	background: #56aaf6;
`;

const Buttontypo = styled.Text`
	font-size: 20px;
	font-weight: 800;
	color: white;
`;

const CustomedModal = ({
	typo,
	buttonTypo1,
	buttonTypo2,
	visible,
	handleModal,
}) => {
	return (
		<Modal visible={visible} transparent={true}>
			<ModalContiner>
				<Block>
					<BlockTypo>{typo}</BlockTypo>
					<ButtonsContainer>
						<ButtonWrapper onPress={handleModal}>
							<Buttontypo>{buttonTypo1}</Buttontypo>
						</ButtonWrapper>
						{buttonTypo2 == undefined || null || "" ? null : (
							<ButtonWrapper onPress={() => setModalVisible(false)}>
								<Buttontypo>{buttonTypo2}</Buttontypo>
							</ButtonWrapper>
						)}
					</ButtonsContainer>
				</Block>
			</ModalContiner>
		</Modal>
	);
};

export default CustomedModal;
