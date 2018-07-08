package io.cogile.mongo.bsoncodec

import org.bson.codecs.{DecoderContext, EncoderContext}
import org.bson.{BsonDocumentReader, BsonDocumentWriter}
import org.mongodb.scala.bson.BsonDocument
import org.scalatest.{Assertion, FunSpec}

class BigIntStringCodecSpec extends FunSpec {

  def writeAndExpect(b: BigInt, expect: BsonDocument): Assertion = {
    val codec = new BigIntStringCodec()
    val writer = new BsonDocumentWriter(new BsonDocument())

    writer.writeStartDocument()
    writer.writeName("name")
    codec.encode(writer, b, EncoderContext.builder().build())
    writer.writeEndDocument()
    assert(writer.getDocument === expect)
  }

  def checkDecoding(source: BigInt): Assertion = {
    val fieldName = "name"
    val codec = new BigIntStringCodec()
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
    val codec = new BigIntStringCodec()
    assert(codec.getEncoderClass() === classOf[BigInt])
  }

  it("should encode BigInt as String") {
    writeAndExpect(BigInt("123"), BsonDocument("{name : '123'}"))
  }

  it("should decode BigInt in same value") {
    checkDecoding(BigInt("123"))
  }
}
