import React from "react";
import styled from "styled-components";
import {Image} from "react-native";

const Container =styled.View`
width:20%;
display: flex;
justify-content: center;
align-items:center;
`

const ImageBlock = ({img}) => {
    return(
        <Container>
            <Image
        source={{uri: img}}
        style={{ width: 130, height: 190, resizeMode: 'contain' }}
      />
        </Container>
    )
}

export default ImageBlock;