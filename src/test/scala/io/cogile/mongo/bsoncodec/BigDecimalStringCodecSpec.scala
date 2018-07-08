package io.cogile.mongo.bsoncodec

import org.bson.codecs.{DecoderContext, EncoderContext}
import org.bson.{BsonDocumentReader, BsonDocumentWriter}
import org.mongodb.scala.bson.BsonDocument
import org.scalatest.{Assertion, FunSpec}

class BigDecimalStringCodecSpec extends FunSpec {

  def writeAndExpect(b: BigDecimal, expect: BsonDocument): Assertion = {
    val codec = new BigDecimalStringCodec()
    val writer = new BsonDocumentWriter(new BsonDocument())

    writer.writeStartDocument()
    writer.writeName("name")
    codec.encode(writer, b, EncoderContext.builder().build())
    writer.writeEndDocument()
    assert(writer.getDocument === expect)
  }

  def checkDecoding(source: BigDecimal): Assertion = {
    val fieldName = "name"
    val codec = new BigDecimalStringCodec()
    val writer = new BsonDocumentWriter(new BsonDocument())
    writer.writeStartDocument()
    writer.writeName(fieldName)
    codec.encode(writer, source, EncoderContext.builder().build())
    writer.writeEndDocument()
    writer.close()

    val reader = new BsonDocumentReader(writer.getDocument)

    reader.readStartDocument()
    reader.readName(fieldName)
    val decodedValue = codec.decode(reader, DecoderContext.builder().build())
    reader.readEndDocument()

    assert(decodedValue === source)
  }

  it("should have correct encoding class") {
    val codec = new BigDecimalStringCodec()
    assert(codec.getEncoderClass() === classOf[BigDecimal])
  }

  it("should encode BigDecimal as String") {
    writeAndExpect(BigDecimal("1"), BsonDocument("{name : '1'}"))
    writeAndExpect(BigDecimal("99.92"), BsonDocument("{name : '99.92'}"))
  }

  it("should decode BigDecimal in same value") {
    checkDecoding(BigDecimal("1"))
    checkDecoding(BigDecimal("99.92"))
  }
}
