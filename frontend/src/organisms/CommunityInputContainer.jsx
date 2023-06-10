import styled from "styled-components";

import CommunityInput from "../molecules/CommunityInput";

const InputContainer = styled.View`
	width: 100%;
	display: flex;
	flex-direction: column;
	box-sizing: border-box;
`;

const CommunityInputContainer = ({ inputList }) => {
	return (
		<InputContainer>
			{inputList.map((info) => {
				({ typo, changeHandler, textInputRef } = info);
				return (
					<CommunityInput
						typo={typo}
						changeHandler={changeHandler}
						textInputRef={textInputRef}
					/>
				);
			})}
		</InputContainer>
	);
};

export default CommunityInputContainer;
