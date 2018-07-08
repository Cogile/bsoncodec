package io.cogile.mongo.bsoncodec

import org.bson.codecs.{Codec, DecoderContext, EncoderContext}
import org.bson.{BsonReader, BsonWriter}

class BigIntStringCodec extends Codec[BigInt] {
  override def decode(reader: BsonReader, decoderContext: DecoderContext): BigInt = {
    BigInt(reader.readString())
  }

  override def encode(writer: BsonWriter, value: BigInt, encoderContext: EncoderContext): Unit = {
    writer.writeString(value.toString())
  }

  override def getEncoderClass: Class[BigInt] = classOf[BigInt]
}
