package com.muhrifqii.protokoin

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

// clean & rebuild & gradle sync to initiate proto auto generated class
object UserPreferenceSettingSerializer : Serializer<UserPreferenceSetting> {
  override val defaultValue: UserPreferenceSetting
    get() = UserPreferenceSetting.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): UserPreferenceSetting {
    try {
      return userPreferenceSetting { readFrom(input) }
    } catch (err: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read protobuf", err)
    }
  }

  override suspend fun writeTo(t: UserPreferenceSetting, output: OutputStream) {
    t.writeTo(output)
  }
}
