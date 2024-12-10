import {useContext, createContext} from 'react'
import { useQuery, UseQueryOptions } from '@tanstack/vue-query'

import { get, handleError } from '@/data/fetchers'
// // import { IS_PLATFORM } from 'lib/constants'
import type { Course } from '@/types/base'
// import type { ResponseError } from '@pt/apps/studio/types'
import { courseKeys } from './keys'

export type CoursesResponse = Course[]

export async function getCourses(signal?: AbortSignal) {
  const { data, error } = await get('/backend/course/getCourses', { signal })
  if (error) handleError(error)

  // [Joshen] TODO: Type this properly from the API
  return data as unknown as CoursesResponse
}

export type CoursesData = Awaited<ReturnType<typeof getCourses>>
export type CoursesError = ResponseError

export const useCoursesQuery = <TData = CoursesData>(
  options: UseQueryOptions<CoursesData, CoursesError, TData> = {}
) =>
// export const useCoursesQuery = () => 
  useQuery({
    // courseKeys.list(),
    // ({ signal }) => getCourses(signal),
    // queryKey: ['courses'],
    queryKey: courseKeys.list(),
    queryFn: ({ signal }) => getCourses(signal),
    // {
    //   ...options,
    //   // enabled: IS_PLATFORM && (options.enabled ?? true),
    //   enabled: true,
    //   staleTime: 30 * 60 * 1000,
    // }
  })
