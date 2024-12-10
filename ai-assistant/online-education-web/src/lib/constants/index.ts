// Ignore barrel file rule here since it's just exporting more constants
// eslint-disable-next-line barrel-files/avoid-re-export-all
// export * from './infrastructure'

export const IS_PLATFORM = process.env.NEXT_PUBLIC_IS_PLATFORM === 'true'
export const DEFAULT_HOME = IS_PLATFORM ? '/projects' : '/project/default'
//export const API_URL = IS_PLATFORM ? process.env.NEXT_PUBLIC_API_URL : '/api'
export const API_URL = '/api'
export const API_ADMIN_URL = IS_PLATFORM ? process.env.NEXT_PUBLIC_API_ADMIN_URL : undefined
export const PG_META_URL = IS_PLATFORM
  ? process.env.PLATFORM_PG_META_URL
  : process.env.STUDIO_PG_META_URL
export const SUPAVISOR_URL = IS_PLATFORM
  ? process.env.SUPAVISOR_URL
  : process.env.SUPAVISOR_URL  
export const SUPAVISOR_SERVICE_KEY = IS_PLATFORM
  ? process.env.SUPAVISOR_SERVICE_KEY
  : process.env.SUPAVISOR_SERVICE_KEY   
export const KS_URL = IS_PLATFORM
  ? process.env.KS_URL
  : process.env.KS_URL
export const KS_URL_V2 = IS_PLATFORM
  ? process.env.KS_URL_V2
  : process.env.KS_URL_V2
export const KS_AUTH_URL = IS_PLATFORM
  ? process.env.KS_AUTH_URL
  : process.env.KS_AUTH_URL  
export const KS_APP_URL_V1 = IS_PLATFORM
  ? process.env.KS_APP_URL_V1
  : process.env.KS_APP_URL_V1   
export const KS_API_V1 = IS_PLATFORM
  ? process.env.KS_API_V1
  : process.env.KS_API_V1   
export const KS_APIS_APPS_V1 = IS_PLATFORM
  ? process.env.KS_APIS_APPS_V1
  : process.env.KS_APIS_APPS_V1      
export const KS_BILL_URL_V1 = IS_PLATFORM
  ? process.env.KS_BILL_URL_V1
  : process.env.KS_BILL_URL_V1       
export const KS_USER = IS_PLATFORM
  ? process.env.KS_USER
  : process.env.KS_USER
export const KS_PWD = IS_PLATFORM
  ? process.env.KS_PWD
  : process.env.KS_PWD    
export const BASE_PATH = process.env.NEXT_PUBLIC_BASE_PATH ?? ''
export const STORAGE_URL = process.env.STORAGE_URL
export const STORAGE_ADM_URL = process.env.STORAGE_ADM_URL
export const STAPI_JWTSECRET = process.env.STAPI_JWTSECRET
export const STAPI_SERVICE_KEY = process.env.STAPI_SERVICE_KEY

/**
 * @deprecated use DATETIME_FORMAT
 */
export const DATE_FORMAT = 'YYYY-MM-DDTHH:mm:ssZ'

// should be used for all dayjs formattings shown to the user. Includes timezone info.
export const DATETIME_FORMAT = 'DD MMM YYYY, HH:mm:ss (ZZ)'

export const GOTRUE_ERRORS = {
  UNVERIFIED_GITHUB_USER: 'Error sending confirmation mail',
}

export const STRIPE_PUBLIC_KEY =
  process.env.NEXT_PUBLIC_STRIPE_PUBLIC_KEY || 'pk_test_51MsgeCEYy1bqi1qlDnYCIoh3gw89ctPLKvsT5xgS3Heg2CQGdwxflPeC9TmMeaQR0jsuCuMLIE6FKc9weQmxs1Ub00dZjtEXRY'

export const USAGE_APPROACHING_THRESHOLD = 0.75

export const LOCAL_STORAGE_KEYS = {
  RECENTLY_VISITED_ORGANIZATION: 'supabase-organization',

  UI_PREVIEW_NAVIGATION_LAYOUT: 'supabase-ui-preview-nav-layout',
  UI_PREVIEW_API_SIDE_PANEL: 'supabase-ui-api-side-panel',
  UI_PREVIEW_CLS: 'supabase-ui-cls',
  UI_PREVIEW_ASSISTANT_V2: 'supabase-ui-assistant-v2',
  UI_ONBOARDING_NEW_PAGE_SHOWN: 'supabase-ui-onboarding-new-page-shown',

  SQL_SCRATCH_PAD_BANNER_ACKNOWLEDGED: 'supabase-sql-scratch-pad-banner-acknowledged',

  DASHBOARD_HISTORY: (ref: string) => `dashboard-history-${ref}`,

  SQL_EDITOR_INTELLISENSE: 'supabase_sql-editor-intellisense-enabled',
  SQL_EDITOR_SPLIT_SIZE: 'supabase_sql-editor-split-size',
  SQL_EDITOR_AI_PANEL_SPLIT_SIZE: 'supabase_sql-editor-ai-panel-split-size',
  // Key to track which schemas are ok to be sent to AI. The project ref is intentionally put at the end for easier search in the browser console.
  SQL_EDITOR_AI_SCHEMA: (ref: string) => `supabase_sql-editor-ai-schema-enabled-${ref}`,
  SQL_EDITOR_AI_OPEN: 'supabase_sql-editor-ai-open',
  LOG_EXPLORER_SPLIT_SIZE: 'supabase_log-explorer-split-size',
  GRAPHIQL_RLS_BYPASS_WARNING: 'graphiql-rls-bypass-warning-dismissed',
  CLS_DIFF_WARNING: 'cls-diff-warning-dismissed',
  CLS_SELECT_STAR_WARNING: 'cls-select-star-warning-dismissed',
  PROJECT_LINT_IGNORE_LIST: 'supabase-project-lint-ignore-list',
  QUERY_PERF_SHOW_BOTTOM_SECTION: 'supabase-query-perf-show-bottom-section',
  // Key to track account deletion requests
  ACCOUNT_DELETION_REQUEST: 'supabase-account-deletion-request',
  // Used for storing a user id when sending reports to Sentry. The id is hashed for anonymity.
  SENTRY_USER_ID: 'supabase-sentry-user-id',
  // Used for storing the last sign in method used by the user
  LAST_SIGN_IN_METHOD: 'supabase-last-sign-in-method',
  // Key to track the last selected schema. The project ref is intentionally put at the end for easier search in the browser console.
  LAST_SELECTED_SCHEMA: (ref: string) => `last-selected-schema-${ref}`,
  // Key to show a warning on the SQL Editor AI Assistant that the org hasn't opted-in to sending anon data
  SHOW_AI_NOT_OPTIMIZED_WARNING: (ref: string) => `supabase-show-ai-not-optimized-${ref}`,
  // Track position of nodes for schema visualizer
  SCHEMA_VISUALIZER_POSITIONS: (ref: string, schemaId: number) =>
    `schema-visualizer-positions-${ref}-${schemaId}`,
  // Used for allowing the main nav panel to expand on hover
  EXPAND_NAVIGATION_PANEL: 'supabase-expand-navigation-panel',
  GITHUB_AUTHORIZATION_STATE: 'supabase-github-authorization-state',
  // Notice banner keys
  AUTH_SMTP_CHANGES_WARNING: 'auth-smtp-changes-warning-dismissed',

  AUTH_USERS_COLUMNS_CONFIGURATION: (ref: string) => `supabase-auth-users-columns-${ref}`,
  AUTH_EMAIL_WARNING_BANNER_ACKNOWLEDGE: (ref: string) =>
    `supabase-auth-email-warning-banner-${ref}`,
}

export const OPT_IN_TAGS = {
  AI_SQL: 'AI_SQL_GENERATOR_OPT_IN',
}

export const GB = 1024 * 1024 * 1024
export const MB = 1024 * 1024
export const KB = 1024
