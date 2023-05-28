
import * as React from 'react'
import { View } from 'react-native'
import PDFReader from 'rn-pdf-reader-js'
import Header from '../organisms/Header'

export default class PDF extends React.Component {
  render() {
    return (
      <>
      <Header/>
      <PDFReader
        source={{
          uri: 'http://samples.leanpub.com/thereactnativebook-sample.pdf',
        }}
      />
      </>
    )
  }
}
