{{/*
Expand the name of the chart.
*/}}
{{- define "hxy.rds.name" -}}
{{- default (print .Chart.Name "-rds") .Values.rds.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "hxy.rds.fullname" -}}
{{- if .Values.rds.fullnameOverride }}
{{- .Values.rds.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default (print .Chart.Name "-rds") .Values.rds.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "hxy.rds.selectorLabels" -}}
app.kubernetes.io/name: {{ include "hxy.rds.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "hxy.rds.serviceAccountName" -}}
{{- if .Values.rds.serviceAccount.create }}
{{- default (include "hxy.rds.fullname" .) .Values.rds.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.rds.serviceAccount.name }}
{{- end }}
{{- end }}
