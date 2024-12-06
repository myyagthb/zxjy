import { createClient } from '@supabase/supabase-js';

// 初始化你的Supabase客户端
const supabaseUrl = 'https://vwdnrupkymrzztrvegwv.supabase.co'
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ3ZG5ydXBreW1yenp0cnZlZ3d2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzMxNDU0NTAsImV4cCI6MjA0ODcyMTQ1MH0.hpgAUm00Q69LxU_6bbpbxUG57n0cfubiQ6Lc_fCy7yQ'

const supabase = createClient(supabaseUrl, supabaseKey);

class StorageManager {
    async uploadFile(bucketName, file, fileName) {
        const { data, error } = await supabase.storage.from(bucketName).upload(fileName, file);
        if (error) {
            console.error('Error uploading file:', error.message);
            return null;
        }
        return data;
    }

    async downloadFile(bucketName, fileName) {
        const { data, error } = await supabase.storage.from(bucketName).download(fileName);
        if (error) {
            console.error('Error downloading file:', error.message);
            return null;
        }
        return data;
    }

    async updateFile(bucketName, oldFileName, newFile, newFileName) {
        // First, delete the old file
        await this.deleteFile(bucketName, oldFileName);
        // Then, upload the new file with the same name
        return this.uploadFile(bucketName, newFile, newFileName);
    }

    async deleteFile(bucketName, fileName) {
        const { error } = await supabase.storage.from(bucketName).remove([fileName]);
        if (error) {
            console.error('Error deleting file:', error.message);
            return false;
        }
        return true;
    }

    async listFiles(bucketName) {
        const { data, error } = await supabase.storage.from(bucketName).list();
        if (error) {
            console.error('Error listing files:', error.message);
            return null;
        }
        return data;
    }

    async getVideoUrl(bucketName, fileName) {
        console.log(bucketName)
        console.log(fileName)
        // const { data } = supabase.storage.from('video').getPublicUrl('test/course-video.mp4');
        const { data } = supabase.storage.from(bucketName).getPublicUrl(fileName);
        console.log(data)
        return data;
    }
}

export default new StorageManager();
