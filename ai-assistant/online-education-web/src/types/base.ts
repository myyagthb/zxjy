
export interface Course {
  courseId: string
  courseName: string
  courseCategory: string
  chooseTagList: string[]
  courseTeacher?: string
  publicState: string
  courseBlurb: string
  courseUrl: string
  courseCovers: string
  createTime: string
  updateTime: string
  userId: string
}

export interface ResponseFailure {
  error: ResponseError
}

export type SupaResponse<T> = T | ResponseFailure

export interface ResponseError {
  code?: number | string
  message: string
  requestId?: string
}


