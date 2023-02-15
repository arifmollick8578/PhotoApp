package com.mollick.photoapp.domain.use_cases.get_image

import com.mollick.photoapp.common.Resource
import com.mollick.photoapp.data.remote.dto.toImageDetail
import com.mollick.photoapp.domain.model.ImageDetail
import com.mollick.photoapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

/** ImageUseCase to fetch and convert DTO object to model class with current states of API call. */
class GetImageUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: Int): Flow<Resource<ImageDetail>> = flow {
        try {
            emit(Resource.Loading())
            val image = repository.getUserImageById(id).toImageDetail()
            emit(Resource.Success(data = image))
        } catch (httpExc: HttpException) {
            emit(Resource.Error(message = httpExc.localizedMessage ?: "An unexpected error."))
        } catch (ioExc: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection."))
        }
    }
}